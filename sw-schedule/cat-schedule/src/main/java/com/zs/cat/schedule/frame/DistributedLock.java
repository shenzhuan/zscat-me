package com.zs.cat.schedule.frame;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class DistributedLock implements Watcher {

	private static final Logger LOG = LoggerFactory.getLogger(DistributedLock.class);
	private static final int SESSION_TIMEOUT = 10000;

	private String zooKeeperURL="192.168.1.23:2181";
	private String groupPath = "/disLocks";
	private String subPath = "/disLocks/sub";
	private CountDownLatch connectedSemaphore = new CountDownLatch(1);
	
	private ZooKeeper zooKeeper;
	private String selfPath;
	private String waitPath;
	private Task task;

	/**
	 * 构造方法
	 * @param url zooKeeper地址如：172.26.7.113:21811
	 * @param groupPath 如：/disLocks
	 * @param subPath 如：/disLocks/sub
	 * @param task 获得锁需要执行的工作
	 */
	public DistributedLock(String url, String groupPath, String subPath, Task task) {
		Assert.hasText(url);
		Assert.hasText(groupPath);
		Assert.hasText(subPath);
		Assert.notNull(task);
		this.zooKeeperURL = url;
		this.groupPath = groupPath;
		this.subPath = subPath;
		this.task = task;
	}

	/** 获取锁，并执行task */
	public String getLockDoTask(String data, boolean needWatch) throws Exception {
		data=StringUtils.trimToEmpty(data);
		// 创建父节点
		this.zooKeeper = new ZooKeeper(zooKeeperURL, SESSION_TIMEOUT, this);
		// 等待连接zk成功的回调
		this.connectedSemaphore.await();
		//回调process方法后，继续
		if (this.zooKeeper.exists(this.groupPath, needWatch) == null) {
			String tempPath = this.zooKeeper.create(this.groupPath, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			LOG.debug("节点创建成功, Path: {}, content: {}", tempPath, data);
		}
		// 创建子节点
		this.selfPath = this.zooKeeper.create(this.subPath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		LOG.debug("创建锁路径:{}", this.selfPath);
		//
		String result=null;
		if (this.checkMinPath()) {
			result=this.doTask();
		}
		return result;
	}

	/** 检查自己是不是最小的节点 */
	private boolean checkMinPath() throws KeeperException, InterruptedException {
		List<String> subNodes = zooKeeper.getChildren(groupPath, false);
		Collections.sort(subNodes);
		int index = subNodes.indexOf(selfPath.substring(groupPath.length() + 1));
		switch (index) {
			case -1: {
				LOG.error("节点已不在了, {}", selfPath);
				return false;
			}
			case 0: {
				LOG.debug("子节点中，我有权使用锁了, {}", selfPath);
				return true;
			}
			default: {
				this.waitPath = groupPath + "/" + subNodes.get(index - 1);
				LOG.debug("获取子节点中，排在我前面的节点: {}", waitPath);
				try {
					zooKeeper.getData(waitPath, true, new Stat());
					return false;
				} catch (KeeperException e) {
					if (zooKeeper.exists(waitPath, false) == null) {
						LOG.debug("子节点中，排在我前面的节点: {}已失踪，重新检查自己是否有权使用锁", waitPath);
						return checkMinPath();
					} else {
						throw e;
					}
				}
			}
		}
	}

	/** 获取锁成功 */
	private String doTask() throws KeeperException, InterruptedException {
		if (zooKeeper.exists(this.selfPath, false) == null) {
			LOG.error("本节点已不在了...");
			return null;
		}
		LOG.debug("获取锁成功，赶紧干活！");
		String result = this.task.run();
		LOG.debug("删除本节点：" + selfPath);
		zooKeeper.delete(this.selfPath, -1);
		releaseConnection();
		return result;
	}

	/** 关闭ZK连接 */
	public void releaseConnection() {
		if (this.zooKeeper != null) {
			try {
				this.zooKeeper.close();
			} catch (InterruptedException e) {
			}
		}
		LOG.debug("释放连接");
	}
	
	// 监听zooKeeper，节点变化会调用此方法
	public void process(WatchedEvent event) {
		if (event == null) {
			return;
		}
		Event.KeeperState keeperState = event.getState();
		Event.EventType eventType = event.getType();
		if (Event.KeeperState.SyncConnected == keeperState) {
			if (Event.EventType.None == eventType) {
				LOG.debug("成功连接上ZK服务器");
				connectedSemaphore.countDown();
			} else if (event.getType() == Event.EventType.NodeDeleted && event.getPath().equals(waitPath)) {
				LOG.debug("收到事件，排我前面的节点已删除，重新检查自己是否有权使用锁");
				try {
					if (this.checkMinPath()) {
						this.doTask();
					}
				} catch (KeeperException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	interface Task {
		public String run();
	}
	
	//测试
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 5; i++) {
			final int threadId = i + 1;
			new Thread() {
				
				// 定义获得锁后要做的事
				private DistributedLock.Task threadTask = new DistributedLock.Task() {
					public String run() {
						System.out.println(threadId + "====do something=====");
						return "OK";
					}
				};
				
				public void run() {
					try {
						DistributedLock lock = new DistributedLock("172.26.7.113:2181","/disLocks", "/disLocks/sub", threadTask);
						lock.getLockDoTask("", true);
					} catch (Exception e) {
						LOG.error("【第" + threadId + "个线程】 抛出的异常：");
						e.printStackTrace();
					}
				}
			}.start();
			
		}
		System.in.read();
	}
	
}







