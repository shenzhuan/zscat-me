springboot dubbo redis solr mq kafka 商城 blog cms
 

### qq群 483681368

<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=a00f8452d401d9302d7f1fe04e77a5d2760824a9ed6bb77662d93fedfecb26d8"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="springboot-dubbo分布式框" title="springboot-dubbo分布式框"></a>
 
![输入图片说明](https://git.oschina.net/uploads/images/2017/0830/111141_ed72ed26_134431.png "1504062698384.png")

# zscat


- ├── sw-common -- SSM框架公共模块（kafka提供者）
- ├── sw-mq -- 消息系统（已集成kafka）
- ├── sw-storm -- storm系统（可以kafka，redis，hbase，mysql，mongodb数据）
- ├── sw-schedule -- 分布式定时任务
- ├── dubbo-cache-starter --dubbo自定义缓存（redis,ehcache,mixcache）
- ├── app-monitor --dubbo服务监控和统计
- ├── sw_manager -- 后台管理模板
- ├── sw-portl -- 官网门户展示
- ├── sw-search-- search管理系统（实现了luence，solr两种搜索）
- |    ├── search-api -- search相关的service
- |    ├── search-service -- search相关的service实现  dubbo服务
- |    ├── search-web -- search消费者 前端展示
- ├── sw-blog-- blog管理系统
- |    ├── blog-api -- blog相关的service
- |    ├── blog-service -- blog相关的service实现  dubbo服务
- |    ├── blog-web -- blog消费者 前端展示
- ├── sw-cms-- cms管理系统
- |    ├── cms-api -- cms相关的service
- |    ├── cms-service -- cms相关的service实现  dubbo服务
- |    ├── cms-web -- cms消费者 前端展示
- ├── sw-shop-- shop管理系统
- |    ├── shop-goods-api -- shop商品相关的service
- |    ├── shop-goods-service -- shop商品相关的service实现  dubbo服务
- |    ├── shop-member-api -- shop会员相关的service
- |    ├── shop-member-service -- shop会员相关的service实现  dubbo服务
- |    ├── shop-order-api -- shop订单相关的service
- |    ├── shop-order-service -- shop订单相关的service实现  dubbo服务
- |    ├── shop-web -- shop消费者 前端展示
- |    ├── shop-h5-- h5消费者 前端展示

- [搜索模块具体介绍](http://git.oschina.net/catshen/zscat_sw/blob/master/sw-search/README.md)
- [后台管理具体介绍](http://git.oschina.net/catshen/zscat_sw/tree/master/sw_manager/README.md)
- [blog管理具体介绍](http://git.oschina.net/catshen/zscat_sw/tree/master/sw_blog/README.md)
- [cms管理具体介绍](http://git.oschina.net/catshen/zscat_sw/tree/master/sw_cms/README.md)
- [商城管理具体介绍](http://git.oschina.net/catshen/zscat_sw/tree/master/sw_shop/README.md)
- [mq管理具体介绍](http://git.oschina.net/catshen/zscat_sw/tree/master/sw-mq/README.md)
- [dubbo缓存具体介绍](http://git.oschina.net/catshen/zscat_sw/tree/master/dubbo-cache-starter/README.md)
- [dubbo监控和统计具体介绍](http://git.oschina.net/catshen/zscat_sw/tree/master/dubbo-monitor/README.md)

1.项目部署，根据doc目录下的 zsboot.sql，weixin.sql，分别创建数据库，相关数据库配置 参考application.properties
先安装 spring-boot-starter-dubbo模块到本地
### 2.blog模块为例  



- a.启动blog-services下面的BlogServiceApplication主类，启动blog的dubbo服务
- b.启动blog-web下面的BlogWebApplication主类，访问 http://localhost:2001/front/blog/indexs
### 3.cms模块为例 
 


- a.启动cms-services下面的cmsServiceApplication主类，启动blog的dubbo服务
- b.启动cms-web下面的cmsWebApplication主类，访问 http://localhost:2001/web/cms/index http://localhost:2001/wap/cms/index
### 4.shop模块为例  



- a.启动shop-services下面的ShopServiceApplication主类，启动blog的dubbo服务
- b.启动shop-web下面的ShopWebApplication主类，访问  http://localhost:2007/front http://localhost:2007/youhong
- c.启动shop-h5下面的ShopWebApplication主类，访问 http://localhost:2006/wap1 
### 5.search模块为例
  


- a.启动search-services下面的SearchServiceApplication主类，启动blog的dubbo服务
- b.启动search-web下面的SearchWebApplication主类，访问 http://localhost:2008
### 6.启动sw_manager下面的 GunsApplication主类，访问 http://localhost  admin  111111
### 7.启动sw_portl下面的 PortlWebApplication主类，访问 http://localhost:2009/gw/index



- [搜索模块具体介绍](http://git.oschina.net/catshen/zscat_sw/blob/master/sw-search/README.md)
- [后台管理具体介绍](http://git.oschina.net/catshen/zscat_sw/tree/master/sw_manager/README.md)
- [blog管理具体介绍](http://git.oschina.net/catshen/zscat_sw/tree/master/sw_blog/README.md)
- [cms管理具体介绍](http://git.oschina.net/catshen/zscat_sw/tree/master/sw_cms/README.md)
- [商城管理具体介绍](http://git.oschina.net/catshen/zscat_sw/tree/master/sw_shop/README.md)
- [mq管理具体介绍](http://git.oschina.net/catshen/zscat_sw/tree/master/sw-mq/README.md)
- [dubbo缓存具体介绍](http://git.oschina.net/catshen/zscat_sw/tree/master/dubbo-cache-starter/README.md)
- [dubbo监控和统计具体介绍](http://git.oschina.net/catshen/zscat_sw/tree/master/dubbo-monitor/README.md)

##商城效果图
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

##后台效果图
![输入图片说明](https://git.oschina.net/uploads/images/2017/0604/194616_36ed7fd6_551203.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0604/194623_a0761bc3_551203.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0604/194630_640dfd35_551203.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0526/104015_bdb14c74_551203.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0516/000735_b83c5c46_551203.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0526/103734_bd3e8f6b_551203.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0604/194539_f9bb482a_551203.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0526/103746_6b4129ed_551203.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0526/103755_7729b916_551203.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0526/103801_b8216865_551203.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0526/103807_20bfb868_551203.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0526/103814_67e078bb_551203.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0526/103822_58fd5d91_551203.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0526/103827_d6218c74_551203.png "在这里输入图片标题")
请作者喝杯咖啡

![输入图片说明](https://git.oschina.net/uploads/images/2017/0829/203712_6694b4c1_134431.jpeg "weixin.jpg")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0829/203723_5567bd56_134431.jpeg "alipay.jpg")