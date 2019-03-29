springboot dubbo redis solr mq kafka 商城 blog cms
 

### qq群 171826977

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




1. 1.项目部署，根据doc目录下的 zsboot.sql，weixin.sql，分别创建数据库，相关数据库配置 参考application.properties
2. 加群下载 spring-boot-starter-dubbo 然后安装 spring-boot-starter-dubbo模块到本地
3. 加群下载 zscat-tools.jar  解压运行zscat-tools 下面的run.bat 同时启动zookeep Redis nginx，默认配置 参考application.properties修改
### 2.blog模块为例  



- a.启动blog-services下面的BlogServiceApplication主类，启动blog的dubbo服务
- b.启动blog-web下面的BlogWebApplication主类，访问 http://localhost:2001/front/blog/indexs
### 3.cms模块为例 
 


- a.启动cms-services下面的cmsServiceApplication主类，启动blog的dubbo服务
- b.启动cms-web下面的cmsWebApplication主类，访问 http://localhost:2001/web/cms/index http://localhost:2001/wap/cms/index
### 4.shop模块为例  


### 需要启动redis 127.0.0.1 6379 没有密码
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



待完成功能
后台管理	商品管理功能	商品分类管理
		商品列表
		规格管理
		类型管理（是否允许自定义属性、规格）
		品牌管理
		商品快速发布
		推荐商品管理
		发布商品定时保存到仓库中
		商品库
	交易管理功能	订单列表
		售后订单列表
		发货单信息导出
		仲裁
		评价管理
	积分商城功能	积分商品管理
		积分订单管理
	店铺管理功能	店铺等级管理
		店铺管理
		店铺分类管理
		店铺入驻申请
	广告位管理功能	添加广告位、广告
	营销管理功能	广告位管理
		优惠券
		满减
		满送
		满免邮
		限时抢购
		团购
	统计报表功能	订单统计报表
		流量统计报表
		结算统计报表
		会员统计报表
		第三方登录率报表
		各平台下单量统计报表
		购买率统计报表
	会员管理功能	会员管理
		会员等级
		会员邀请码
		会员积分
		允许会员登录
		允许会员发言
		允许会员购买商品
	分销管理功能	会员注册邀请码
	邮件系统	邮箱发送功能
	短信发送平台 	短信发送功能
	结算管理功能	结算周期
		结算列表
	系统管理功能	角色管理
		用户管理
		权限管理
		修改密码
		操作日志
	运营管理	首页楼层
		手机楼层
		文章分类
		文章列表
		展示类目
		站内信
	内容管理	文章栏目
		文章列表
		文章发布
	圈子管理	圈子分类
		圈子管理
		帖子管理
	平台自营	平台自营商品
		平台自营订单
		平台自营统计
		平台发货设置
		平台自营售后
	代码生成器	业务表
		生成方案
	分销管理功能	分销商自动审核
		分销等级
		分销设置
		分销商管理
商家管理	店铺入驻	填写信息
		选择店铺商品分类
		提交审核
	导入商品	导入excel表格
	商品发布	发布实物商品
		商品快速发布
		发布商品定时保存到仓库中
		发布虚拟商品
	商品管理功能	出售商品
		仓库商品
		下架商品
		违规下架商品
		推荐商品管理
		待审核商品
	品牌管理功能	添加品牌
	店铺商品分类	添加店铺分类
	商品库	选择商品库商品
	虚拟订单管理	虚拟订单列表
		兑换码兑换
	订单管理	调整费用
		确认订单
		取消订单
		仲裁
		退款退货原因管理
		发货
	发货	发货管理
		物流公司
		发货单信息导出
		地址库
	物流工具	运费模板
	评价管理	评价列表
		导入评价excel表
	促销管理	优惠资源
		优惠券
		满免邮
		满就减
		满送
		限时抢购
		团购
	店铺设置	结算设置
		店铺信息设置
	客服管理	咨询管理
		退款管理
		退货管理
		换货管理
	统计结算	订单结算
		流量统计
		订单统计
		购买率统计
		结算统计
		结算账单
	权限设置	设置权限组
		设置用户
	内容管理	文章栏目
		文章列表
		发布文章
	分销管理功能	分销管理模块
		分销商品
		分销订单
	自提点	自提订单
		自提点添加
前台	用户注册	手机号注册
		邮箱注册
	用户登录	账户登录
		QQ登录
		微信登录
		新浪微博登录
	商城首页	商城有几个首页模板可供选择
	商品搜索模块	用户可通过关键字搜索商品和商家
	商品分类模块	由后台添加的展示类目而来
	品牌展示模块	品牌展示品牌LOGO展示
	广告管理模块 	首页大广告
		分类广告
		楼层广告
		限时抢购广告
		团购广告
		圈子广告
	标签展示模块	新品上市
		猜你喜欢
		推荐商品
	楼层模块	楼层名称  
		楼层品牌
		楼层商品
	商品列表	可根据销售量、价格等排序
	支付方式	货到付款
		余额支付
		在线支付（银联、微信、支付宝）
	购买	立即购买
		购物车
	商品详情页	商品展示
		商品详情展示
		包装售后展示
		规格说明展示
		评价展示
		销售记录展示
		商品分享入口
		店铺信息展示
		联系客服
	店铺	店铺首页
		信用评价
		店铺详情
		店铺资讯
	文章模块	文章列表
		文章详情
	"订单管理
"	查看订单状态  
		查看物流
		申请售后
		评价订单
	虚拟商品订单	订单评价
		申请退款
	收藏中心	收藏商品
		收藏店铺
	余额	余额查询
		余额充值
	优惠券	已使用
		未使用
		已过期
	积分	积分明细
		积分订单
	客服服务	退款管理
		退货管理
		换货管理
		仲裁管理
		咨询管理
	设置	个人信息
		收货地址
		修改密码
		账户安全
		支付密码
	站内信	站内信列表
	圈子管理	我的圈子
		帖子管理
	浏览记录	店铺记录
		商品记录
	限时抢购	抢购商品列表
		抢购商品详情
	团购	团购列表
		团购商品详情
	优惠券	商城优惠券列表
	积分商城	积分商品列表
		积分商品详情
	自提	自提功能
App移动商城	商品展示功能	商品展示
		商品详情展示
		包装售后展示
		规格说明展示
	订单管理	订单信息
		订单详情
	商品分享功能	商品分享入口
	在线支付功能	支付宝
		银联
		微信
	购物车功能	添加商品到购物车
	移动商铺管理功能（商家）	申请商铺
		商铺管理
	移动商品管理功能（商家）	管理订单
		简化版商品发布
		商品上架、下架管理
		客服消息
	站内搜索功能	站内商品搜索
	会员中心功能	会员管理
		积分管理
		订单管理
		账户管理
	广告展示功能	展示广告信息
	签到	点击签到增加积分
	分销管理功能	推广产品
		我的分店
		推广订单
		我的提成
		设置 
		二维码
	自提点	下单选择自提或配送
	砍价	砍价
	拼团	拼团
	众筹	众筹
微信公众号&微网站	首页展示	广告
		标签商品
		楼层广告
		楼层商品
		搜索
	商品展示功能	商品展示
		商品详情展示
		包装售后展示
		规格说明展示
		商品评价展示
	店铺	店铺商品
		店铺优惠券
		店铺动态
	账户管理	账户信息
	积分	积分记录
	购买	立即购买
		加入购物车
	"订单管理
"	订单管理
	余额充值	余额充值
	优惠券	优惠券
	浏览记录	记录
	售后	申请售后
		进度查询
	收藏	收藏商品
		收藏店铺
	注册	手机号注册
	分销	推广产品
		我的分店
		推广订单
		我的提成
		设置 
		二维码
	自提点	下单选择自提或配送
	自提点	下单选择自提或配送
	砍价	砍价
	拼团	拼团
	众筹	众筹

https://gitee.com/jmdhappy/xxpay-master 支付
###  请作者喝杯咖啡

![输入图片说明](https://git.oschina.net/uploads/images/2017/0829/203712_6694b4c1_134431.jpeg "weixin.jpg")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0829/203723_5567bd56_134431.jpeg "alipay.jpg")
