技术选型


- 1、后端
- 核心框架：Spring Framework 4.0
-  分布式框架： zookeeper dubbox2.8.4 分布式锁
-   分布式调用链 zikpin brove
-  分布式日志分析 elk  Elasticsearch，Logstash，Kibana 
-     Elasticsearch：搜索，提供分布式全文搜索引擎
- 　　Logstash: 日志收集，管理，存储
- 　　Kibana ：日志的过滤web 展示
-  分布式监控  dubbo-monitor  dubbo-admin
-    mq通信框架  kafka  redis mongodb
-     分库分表 sharding jdbc
- - 安全框架：Apache Shiro 1.2
   
- 视图框架：Spring MVC 4.0
- 服务端验证：Hibernate Validator 5.1
- 任务调度：Spring Task 4.0
- 持久层框架：MyBatis 3.2
- 数据库连接池：Alibaba Druid 1.0
- 缓存框架：Ehcache 2.6、Redis
- Luence搜索引擎
- 日志管理：SLF4J 1.7、Log4j2   logback
- 工具类：Apache Commons、Jackson 2.2、Xstream 1.4、Dozer 5.3、POI 3.9
- 2、前端
- JS框架：jQuery 1.9。
- CSS框架：bootstrap ace admin框架界面。
- 客户端验证：JQuery Validation Plugin 1.11。
- 富文本：CKEcitor
- 文件管理：CKFinder
- 百度 web upload 图片上传插件手机端框架：Jingle
- 数据表格：jqGrid
- 对话框：jQuery jBox
- 下拉选择框：jQuery Select2
- 树结构控件：jQuery zTree
- 日期控件： My97DatePicker

已完成功能


- 后台  用户管理   角色管理  菜单管理 组织管理 日志管理
- 监控  jvm监控 ehcache监控  durid数据库监控
- 商城  商品管理  首页菜单管理 楼层管理  商品类别  订单管理  文章管理
- 商城前台  主页菜单 楼层 文章 商品展示，商品详情展示 ，购物 添加商品到购物车，结算 微信支付，支付宝支付。

技术要点  


- 登录用户的浏览记录存redis ，hash存储 一周过期
- log4j2 通过配置直接将数据存入logstash ，然后通过elk展示分析

待做功能


- 用户登录或者注册送积分存入kafka，然后一个单独的项目消费kafka数据 ，将数据持久化到数据库
- 日志数据存入mongodb


此项目只是拆分了 商城 http://git.oschina.net/catshen/zsTrade 


- shop-admin 商城后台管理
- shop-web 商城前台 （首页 支付 等）
- shop-common 商城公共类 工具类等
- shop-order-api  订单dubbox接口
- shop-order      订单dubbox实现类 采用sharding jdbc 进行订单分库分表
- shop-member-api 会员dubbox接口
- shop-member     会员dubbox实现类 采用sharding jdbc 进行订单分库分表
- shop-goods-api  商品dubbox接口
- shop-goods      商品dubbox实现类  （商品，楼层 ，类别 品牌 等）

演示地址
http://zscat.top/
使用技术和后台同  
http://git.oschina.net/catshen/cat

### 运行部署
- a.启动shop-services下面的ShopServiceApplication主类，启动blog的dubbo服务
- b.启动shop-web下面的ShopWebApplication主类，访问  http://localhost:2007/front http://localhost:2007/youhong
- c.启动shop-h5下面的ShopWebApplication主类，访问 http://localhost:2006/wap 
### 5.search模块为例

![输入图片说明](http://git.oschina.net/uploads/images/2017/0412/101032_1320272c_134431.png "在这里输入图片标题")
商品管理
![输入图片说明](http://git.oschina.net/uploads/images/2017/0412/101314_811d498c_134431.png "在这里输入图片标题")
楼层管理
![输入图片说明](http://git.oschina.net/uploads/images/2017/0412/101322_82a87832_134431.png "在这里输入图片标题")

### 5.用tomcat添加shop-web， 访问 http://localhost:8080/shop-web/front

![输入图片说明](http://git.oschina.net/uploads/images/2017/0412/101020_3545076d_134431.png "在这里输入图片标题")
![输入图片说明](http://git.oschina.net/uploads/images/2017/0412/101514_9c2830ca_134431.png "在这里输入图片标题")
![输入图片说明](http://git.oschina.net/uploads/images/2017/0412/101530_73ba9328_134431.png "在这里输入图片标题")


brave-dubbo.xml 采集数据到zikpin，生成分布式调研链数据
 **_启动zipkin_** 
![输入图片说明](http://git.oschina.net/uploads/images/2017/0412/101932_4d1f56da_134431.png "在这里输入图片标题")
![输入图片说明](http://git.oschina.net/uploads/images/2017/0412/101956_c632c810_134431.png "在这里输入图片标题")
![输入图片说明](http://git.oschina.net/uploads/images/2017/0412/102006_e8778091_134431.png "在这里输入图片标题")
![输入图片说明](http://git.oschina.net/uploads/images/2017/0412/102015_2204af0a_134431.png "在这里输入图片标题")

### 启动dubbo-montior
![输入图片说明](https://git.oschina.net/uploads/images/2017/0503/101233_d7915d97_791098.png "在这里输入图片标题")
http://git.oschina.net/handu/dubbo-monitor
启动dubbo-admin
![输入图片说明](https://git.oschina.net/uploads/images/2017/0503/101252_5a239aec_791098.png "在这里输入图片标题")
启动elk
![输入图片说明](https://git.oschina.net/uploads/images/2017/0503/101306_d0720500_791098.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0503/101315_fbfcd126_791098.png "在这里输入图片标题")

![输入图片说明](https://git.oschina.net/uploads/images/2017/0718/200708_69a36cca_134431.png "dubbo1.png")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0718/200719_3bddc084_134431.png "dubbo2.png")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0718/200734_21fc3537_134431.png "dubbo3.png")
sharding jdbc整合介绍
http://blog.csdn.net/a1439226817/article/details/64437915


qq 951449465