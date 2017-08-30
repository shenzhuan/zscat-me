package com.zscat.storm.recommend.test.spouts;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;


public class WordReader extends BaseRichSpout {

	private SpoutOutputCollector collector;
	private FileReader fileReader;
	private boolean completed = false;
	public void ack(Object msgId) {
		System.out.println("OK:"+msgId);
	}
	public void close() {}
	public void fail(Object msgId) {
		System.out.println("FAIL:"+msgId);
	}

	/** 
     * 这是Spout最主要的方法，在这里我们读取文本文件，并把它的每一行发射出去（给bolt） 
     * 这个方法会不断被调用，为了降低它对CPU的消耗，当任务完成时让它sleep一下 
     * **/ 
	public void nextTuple() {
		/**
		 * The nextuple it is called forever, so if we have been readed the file
		 * we will wait and then return
		 */
		if(completed){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				//Do nothing
			}
			return;
		}
		String str;
		//Open the reader
		BufferedReader reader = new BufferedReader(fileReader);
		try{
			//Read all lines
			while((str = reader.readLine()) != null){
				/** 
                 * 发射每一行，Values是一个ArrayList的实现 
                 */ 
				this.collector.emit(new Values(str),str);
			}
		}catch(Exception e){
			throw new RuntimeException("Error reading tuple",e);
		}finally{
			completed = true;
		}
	}

	/** 
     * 这是第一个方法，里面接收了三个参数，第一个是创建Topology时的配置， 
     * 第二个是所有的Topology数据，第三个是用来把Spout的数据发射给bolt 
     * **/
	/*{"storm.id" "Getting-Started-Toplogie-1-1487157895", "topology.fall.back.on.java.serialization" true, 
		"zmq.linger.millis" 0, "topology.skip.missing.kryo.registrations" true, "ui.childopts" "-Xmx768m", 
		"storm.zookeeper.session.timeout" 20000, "nimbus.reassign" true, "nimbus.monitor.freq.secs" 10, 
		"java.library.path" "/usr/local/lib:/opt/local/lib:/usr/lib", "storm.local.dir" 
		"C:\\Users\\zhuan\\AppData\\Local\\Temp\\/9fc19282-4945-4345-9465-de16e986e3ac", 
		"supervisor.worker.start.timeout.secs" 240, "wordsFile" "C:/data/word.txt", 
		"nimbus.cleanup.inbox.freq.secs" 600, "nimbus.inbox.jar.expiration.secs" 3600,
		"storm.zookeeper.port" 2181, "transactional.zookeeper.port" nil, "transactional.zookeeper.servers" nil,
		"storm.zookeeper.root" "/storm", "supervisor.enable" true, "storm.zookeeper.servers" ["localhost"], 
		"transactional.zookeeper.root" "/transactional", "topology.name" "Getting-Started-Toplogie", "topology.worker.childopts" nil, "worker.childopts" "-Xmx768m", "supervisor.heartbeat.frequency.secs" 5, "drpc.port" 3772,
		"supervisor.monitor.frequency.secs" 3, "task.heartbeat.frequency.secs" 3, "topology.max.spout.pending" 1,
		"storm.zookeeper.retry.interval" 1000, "supervisor.slots.ports" (1 2 3), "topology.debug" false,
		"nimbus.task.launch.secs" 120, "nimbus.supervisor.timeout.secs" 60, "topology.kryo.register" nil, 
		"topology.message.timeout.secs" 30, "task.refresh.poll.secs" 10, "topology.workers" 1, 
		"supervisor.childopts" "-Xmx1024m", "nimbus.thrift.port" 6627, "topology.stats.sample.rate" 0.05, 
		"worker.heartbeat.frequency.secs" 1, "nimbus.task.timeout.secs" 30, "drpc.invocations.port" 3773, 
		"zmq.threads" 1, "storm.zookeeper.retry.times" 5, "topology.state.synchronization.timeout.secs" 60,
		"supervisor.worker.timeout.secs" 30, "nimbus.file.copy.expiration.secs" 600, "storm.local.mode.zmq" false, 
		"ui.port" 8080, "nimbus.childopts" "-Xmx1024m", "topology.ackers" 1, "storm.cluster.mode" "local", "topology.optimize" true, "topology.max.task.parallelism" nil}
	*/
	public void open(Map conf, TopologyContext context,
			SpoutOutputCollector collector) {
		try {
			this.fileReader = new FileReader(conf.get("wordsFile").toString());
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Error reading file ["+conf.get("wordFile")+"]");
		}
		 //初始化发射器  
		this.collector = collector;
	}

	/**
	 * Declare the output field "word"
	 */
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("line"));
	}
}
