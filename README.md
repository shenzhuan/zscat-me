# mallcloud-platform

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)]
(https://www.apache.org/licenses/LICENSE-2.0.html)<a href='https://gitee.com/catshen/mallcloud-platform'><img src='https://gitee.com/catshen/mallcloud-platform/widgets/widget_4.svg' alt='Fork me on Gitee'></img></a><a href='https://gitee.com/catshen/mallcloud-platform/stargazers'><img src='https://gitee.com/catshen/mallcloud-platform/badge/star.svg?theme=dark' alt='star'></img></a>

## 如果您觉得有帮助，请点右上角 "Star" 支持一下谢谢

[TOC]

## 1. 项目介绍

* **技术交流群** [195405248]
* **详细在线文档** [点击查看](https://github.com/shenzhuan/mallcloud-platform/wiki/%E9%A1%B9%E7%9B%AE%E9%83%A8%E7%BD%B2)
* **演示地址**
  * https://mojin.51wangshi.com/admin 平台运营管理
    https://mojin.51wangshi.com/store 商户管理
    https://mojin.51wangshi.com 13146587711 123456
  * 账号密码：admin/admin
  * 应用监控账号密码：admin/admin
  * 配置中心账号密码：nacos/nacos
  * APM监控账号密码：admin/admin
  * Grafana账号：mall/mall
  * txlcn事务管理器密码：admin
  * 任务管理账号密码：admin/123456
* **演示环境有全方位的监控示例：日志系统 + APM系统 + GPE系统**

* Gitee地址：https://gitee.com/catshen/mallcloud-platform
* Github地址：https://github.com/shenzhuan/mallcloud

* 前后端分离的企业级微服务架构
* 基于`Spring Boot 2.0.X`、`Spring Cloud Finchley`和`Spring Cloud Alibaba`
* 深度定制`Spring Security`真正实现了基于`RBAC`、`jwt`和`oauth2`的无状态统一权限认证的解决方案
* 提供应用管理，方便第三方系统接入
* 引入组件化的思想实现高内聚低耦合，项目代码简洁注释丰富上手容易
* 注重代码规范，严格控制包依赖，每个工程基本都是最小依赖
* 非常适合学习和企业中使用
> cloud框架和组件来源：https://gitee.com/owenwangwen/open-capacity-platform
 https://gitee.com/zlt2000/microservices-platform
> 业务逻辑来源：https://github.com/shenzhuan/mallplus

&nbsp;

## 2. 项目总体架构图

![](http://processon.com/chart_image/5c7f2ad6e4b02b2ce48d6835.png?_=1554621571250)

&nbsp;

## 3. 功能介绍

* **统一认证功能**
  * 支持oauth2的四种模式登录
  * 支持用户名、密码加图形验证码登录
  * 支持手机号加密码登录
  * 支持openId登录
  * 支持第三方系统单点登录

* **分布式系统基础支撑**
  * 服务注册发现、路由与负载均衡
  * 服务降级与熔断
  * 服务限流(url/方法级别)
  * 统一配置中心
  * 统一日志中心
  * 统一分布式缓存操作类、cacheManager配置扩展
  * 分布式锁
  * 分布式任务调度器
  * 支持CI/CD持续集成(包括前端和后端)
  * 分布式高性能Id生成器
  * 分布式事务
* **系统监控功能**
  * 服务调用链监控
  * 应用拓扑图
  * 慢服务检测
  * 服务Metric监控
  * 应用监控(应用健康、JVM、内存、线程)
  * 错误日志查询
  * 慢查询SQL监控
  * 应用吞吐量监控(qps、rt)
  * 服务降级、熔断监控
  * 服务限流监控
  * 分库分表、读写分离
* **业务基础功能支撑**
  * 高性能方法级幂等性支持
  * RBAC权限管理，实现细粒度控制(方法、url级别)
  * 快速实现导入、导出功能
  * 数据库访问层自动实现crud操作
  * 代码生成器
  * 基于Hutool的各种便利开发工具
  * 网关聚合所有服务的Swagger接口文档
  * 统一跨域处理
  * 统一异常处理

&nbsp;

## 4. 模块说明

```lua
mallcloud -- 父项目，公共依赖
│  ├─mall-business -- 业务模块一级工程
│  │  ├─user-center -- 用户中心[7000]
│  │  ├─file-center -- 文件中心[5000]
│  │  ├─member-center -- 会员中心[7001]
│  │  ├─goods-center -- 商品中心[7002]
│  │  ├─order-center -- 订单中心[7003]
│  │  ├─marking-center -- 营销中心[7004]
│  │─mall-commons -- 通用工具一级工程
│  │  ├─mall-auth-client-spring-boot-starter -- 封装spring security client端的通用操作逻辑
│  │  ├─mall-common-spring-boot-starter -- 封装通用操作逻辑
│  │  ├─mall-db-spring-boot-starter -- 封装数据库通用操作逻辑
│  │  ├─mall-log-spring-boot-starter -- 封装log通用操作逻辑
│  │  ├─mall-redis-spring-boot-starter -- 封装Redis通用操作逻辑
│  │  ├─mall-ribbon-spring-boot-starter -- 封装Ribbon和Feign的通用操作逻辑
│  │  ├─mall-sentinel-spring-boot-starter -- 封装Sentinel的通用操作逻辑
│  │  ├─mall-swagger2-spring-boot-starter -- 封装Swagger通用操作逻辑
│  ├─mall-config -- 配置中心
│  ├─mall-doc -- 项目文档
│  ├─mall-gateway -- api网关一级工程
│  │  ├─zuul-gateway -- netflix-zuul[8080]
│  ├─mall-job -- 分布式任务调度一级工程
│  │  ├─job-admin -- 任务管理器[8081]
│  │  ├─job-core -- 任务调度核心代码
│  │  ├─job-executor-samples -- 任务执行者executor样例[8082]
│  ├─mall-monitor -- 监控一级工程
│  │  ├─sc-admin -- 应用监控[6500]
│  │  ├─log-center -- 日志中心[6200]
│  ├─mall-uaa -- spring-security认证中心[8000]
│  ├─mall-register -- 注册中心Nacos[8848]
│  ├─mall-web -- 前端一级工程
│  │  ├─back-web -- 后台前端[8066]
│  ├─mall-transaction -- 事务一级工程
│  │  ├─txlcn-tm -- tx-lcn事务管理器[7970]
│  ├─mall-demo -- demo一级工程
│  │  ├─txlcn-demo -- txlcn的demo
│  │  ├─sharding-jdbc-demo -- sharding-jdbc的demo
```

&nbsp;

## 5. 截图（点击可大图预览）

<table>
	<tr>
        <td><img src="https://gitee.com/mall2000/images/raw/master/server_metrics.png"/></td>
        <td><img src="https://gitee.com/mall2000/images/raw/master/application_metrics.png"/></td>
    </tr>
	<tr>
        <td><img src="https://gitee.com/mall2000/images/raw/master/持续集成2.png"/></td>
        <td><img src="https://gitee.com/mall2000/images/raw/master/sonar结果.png"/></td>
    </tr>
    <tr>
        <td><img src="https://gitee.com/mall2000/images/raw/master/skywalking首页.png"/></td>
        <td><img src="https://gitee.com/mall2000/images/raw/master/skywalking应用拓扑图.png"/></td>
    </tr>
    <tr>
        <td><img src="https://gitee.com/mall2000/images/raw/master/elk.png"/></td>
        <td><img src="https://gitee.com/mall2000/images/raw/master/任务中心.png"/></td>
    </tr>
    <tr>
        <td><img src="https://gitee.com/mall2000/images/raw/master/日志中心02.png"/></td>
        <td><img src="https://gitee.com/mall2000/images/raw/master/慢查询sql.png"/></td>
    </tr>
    <tr>
        <td><img src="https://gitee.com/mall2000/images/raw/master/应用监控01.png"/></td>
        <td><img src="https://gitee.com/mall2000/images/raw/master/应用监控02.png"/></td>
    </tr>
    <tr>
        <td><img src="https://gitee.com/mall2000/images/raw/master/nacos-discovery.png"/></td>
        <td><img src="https://gitee.com/mall2000/images/raw/master/应用吞吐量监控.png"/></td>
    </tr>
</table>