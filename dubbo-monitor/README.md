---

# 项目介绍:(1.1.2)

## 注意：branch
#### `master` - mysql版本（持续维护中～）
#### ~~`monitor-for-mysql`~~ - mysql版本(已转移至master，待删除)
#### `monitor-redis` - redis版本（维护截至到2016-06-03，后续暂时不维护）

## 界面效果图：
![输入图片说明](https://git.oschina.net/uploads/images/2017/0718/200708_69a36cca_134431.png "dubbo1.png")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0718/200719_3bddc084_134431.png "dubbo2.png")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0718/200734_21fc3537_134431.png "dubbo3.png")

## 
---

# 一、特性：
1. 修复dubbo-admin的bug:本地应用启动停止后，若其相同的应用名服务仍有其他版本号，此本地应用的版本号不会注销。
2. 添加实时监控应用的启动和停止，防止应用停止影响公司业务。
3. 支持单点重启，监控到监控中心停止时，注册到zk的应用--启动和停止情况。
4. 以图像形式直观描述服务间的调用情况，方便服务结构调优。
5. 数据统计精准到方法级别。

---

# 二、模块说明:
1. 首页：
	* 只观查看应用、方法、host的数量和每个应用之间的调用情况，有助于优化系统间的调用关系
	* 即时监控应用的停止和启动，其会触发NotifyAppChangeProcessor中的方法，可根据自身业务写自身逻辑代码
	* 页面上显示应用的停止和启动记录
2. application:
	* 查看应用的host:port，是否是消费者或使用者
	* 其内部的方法和最后调用时间，有助于排除无用的方法
	* 每日每月的应用调用情况记录和应用之间的调用记录
3. service:
	* 查看service中的具体方法的调用情况和统计数据————TPS:每秒查询率（次/秒） ART:每次平均耗时（毫秒/次）
	* 高亮显示异常的service，方便排错
4. host:
	* 记录每个服务器上的应用部署情况

---

# 三、项目使用的技术

1. 数据库：mysql（分支：redis）
2. 缓存：redis
2. 前端：freemarker+metronic+echarts
3. 框架：springmvc
4. dubbo版本号：dubbo-2.5.3
5. 支持的启动容器：jetty 和 tomcat
6. 其他：autoconfig、maven、ShardedJedisPool

---

# 四、使用帮助
在IDEA中
#### 1.初始化配置：
    web/src/main/resources/application.properties
		jdbc.url              = jdbc:mysql://127.0.0.1:3306/monitor?useUnicode=true&characterEncoding=utf-8&autoReconnect=true # 此处为自身的mysql地址和schema，此处仅供参考
        jdbc.username         = root # mysql的帐号，此处仅供参考
        jdbc.password         = 123456 # mysql的密码，此处仅供参考

		dubbo.application.name = ants-monitor
		dubbo.port = 20882 #此处为port地址，该监控中心应用的端口号
		zookeeper.address = 127.0.0.1:2181 # 此处为zk地址，若多个，则用","隔开。例如（zookeeper.address = 127.0.0.1:2181,127.0.0.2:2181,127.0.0.3:2181）
		redis.host  = redis://ants-monitor:123456@127.0.0.1:6379/2 #此处为redis数据库 密码@ip:host/db号

	com.ants.monitor.bean.MonitorConstants的initEcsMap方法，在内存中存入自定义线上ip地址和测试环境ip地址。

> **注意：**因用autoconfig，若使用mvn clean package -Dmaven.test.skip -U打包，则需在 `web/src/main/webapps/META-INFO/autoconf/auto-conf.xml`中进行初始化数据更改。

#### 2.容灾设置，未启动的应用自定义实现报警
    `AppStopTaskController` 类的todo之处
    
#### 3.两种启动方式，选一种即可（本地推荐法一）：
    1）jetty: web/test/java/AntsMonitorServer ->main 方法启动即可
    2）tomcat: 先`mvn clean package -Dmaven.test.skip -U` 打包,一直 yes 而后将war包放在tomcat webapps下,更改 $tomcat_home/webapps/ROOT/WEB-INF/classes/application.properties 中的变量 启动tomcat即可

#### 4.mysql数据库建表sql：根目录下`dubbo_invoker.sql`

#### 5. 提供者或消费者应用接入监控中心(非此项目，为其他dubbo项目)：
	1）方式一（zk自动发现dubbo监控中心）:<dubbo:monitor protocol="registry"/>
	2) 方式二（直连）:<dubbo:monitor address="10.20.130.230:12080"/>

---

![输入图片说明](https://git.oschina.net/uploads/images/2017/0718/200708_69a36cca_134431.png "dubbo1.png")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0718/200719_3bddc084_134431.png "dubbo2.png")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0718/200734_21fc3537_134431.png "dubbo3.png")
# 五、获取帮助

如果你在使用过程中遇到任何问题，请在这里告诉我们。<br>
代码维护：<a href="https://github.com/zhongxig/dubbo-d-monitor">https://github.com/zhongxig/dubbo-d-monitor</a><br>
dubbo-monitor讨论QQ群号是：483681368， 加前请注明 dubbo监控中心学习讨论。

---

# 六、更改历史
### 2017.03.10 -`master`
> dubbo-d-monitor 1.2.1:<br>
1. 修复group 的数据统计有误的bug，由于 dubbo自带bug 统计中不带有group，本系统均不支持group统计

### 2017.03.09 -`master`
> dubbo-d-monitor 1.2.0:<br>
1. 优化zk节点的应用停止服务和启动服务的处理逻辑代码<br>
2. 解决监控中心宕机后重启的补偿机制执行缓慢<br>

### 2016.11.08 -`master`
> dubbo-d-monitor 1.1.4:<br>
1. 修复dubboService 数据采集导致的OOM bug<br>
2. 修改部分文案<br>
3. 增大线程池所使用的缓冲队列<br>

### 2016.10.25 -`master`
> dubbo-d-monitor 1.1.3:<br>
1. 方法排行榜 每日凌晨统计，走缓存<br>
2. application 缓存到 redis中<br>
3. service 的method 的数据展示 异步加载<br>

### 2016.08.10 -`master`
> dubbo-d-monitor 1.1.2:<br>
1. 新增全局alert 样式js和css<br>
2. service模块未选择方法时查看图片bug fix<br>

### 2016.07.07 -`master`
> dubbo-d-monitor 1.1.2:<br>
1. 方法排行最大值改为50<br>
2. 去掉排行榜浏览器缓存<br>

### 2016.07.05 -`master`
> dubbo-d-monitor 1.1.2:<br>
1. application模块新增 方法调用排行，便于例如测试将多调用多方法做集成测试<br>

### 2016.06.28 -`master`
> dubbo-d-monitor 1.1.1:<br>
1. 新增分支：monitor-redis，将之前master代码迁移到此分支上<br>
2. mysql分支代码合并到master上，此后在master上开发，`monitor-for-mysql`待删除<br>
3.最后消费时间，精确到每个机子上统计，多机子的相同最后消费时间不一致<br>
4.移除部分无用代码<br>

### 2016.06.01-`monitor-for-mysql`
很久没更新了～！！ 为了优化存储和数据展示，参考了zabbix 的部分思路，将数据按时间存储不一致的粒度，且更改数据库存储为mysql，redis仅做缓存，由于该系统目前维护就我*一个人*，因此后续就维护 mysql版本，redis版本暂时不维护：
> monitor-on-redis 1.1.0:<br>
1. 新增分支：monitor-for-mysql<br>
2. 更改项目名为：dubbo-d-monitor<br>
3. 优化了页面展示<br>
4. 变更持久化数据存储到mysql中，且保存30天，过期的数据定时器删除<br>
5. 及时纪录系统间的调用数据，根据2个小时，一天的粒度进行数据划分，短期存入redis，每日凌晨定时器将数据存入mysql<br>
6. fix 部分隐藏bug<br>


### 2016.03.09
> monitor-on-redis 1.0.4:<br>
1. 内存占用仍旧很夸张，毕竟是用redis做数据库，invoke 大数据 过期时间改为1天，待后续优化方案<br>
2. redis释放资源 改为 shardedJedis.close();fix redis 无法释放资源的bug<br>


### 2016.03.05
由于运维发现此应用占用redis内存过高，且无用，因此更改redis的存储时间<br>
> monitor-on-redis 1.0.3:<br>
1. 拆分invoke每日数据，每日凌晨统计数据报表。redis 数据基本都作过期化存储。<br>

### 2016.01.20
> monitor-on-redis 1.0.2:<br>
1. 新增——本地非法起1.0.0.daily 和测试 非法起1.0.0 应用的警告页面，帮助用户快速定位问题<br>
2. 新增对 禁止掉的以override为头的url 记录，页面暂时不实现禁止的内容。展示所有禁止的数据。<br>

### 2016.01.08
> 1. 优化pom依赖，取出zk的重复引入<br>

### 2016.01.07
> monitor-on-redis 1.0.1:<br>
1. 修复cpu占用过大的bug<br>
2. 更新readme<br>

### 2016.01.05
> 1. 上线1.0.0-redis版本的监控中心<br>


