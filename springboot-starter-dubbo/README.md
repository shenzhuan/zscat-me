#spring-boot-start-dubbo

Dubbo是阿里开发的一套分布式通讯框架,Spring-boot是业界比较火的微服务框架，两者可以进行结合实现分布式微服务
* 对于提供外部的服务，可以使用spring-boot的rest服务，达到Api网关的目的
* 对于内部远程Rpc调用，可以借用Dubbo能力，达到服务治理的目的

##如何发布Dubbo服务
* 在Spring Boot项目的pom.xml中添加以下依赖:
```
 <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-dubbo</artifactId>
         <version>1.3.1.RELEASE</version>
 </dependency>
 <!--依赖于容器-->
 <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-web</artifactId>
         <version>1.3.1.RELEASE</version>
 </dependency>
 ```
* 在application.properties添加Dubbo的版本信息和客户端超时信息,如下:
```
spring.dubbo.application.name=annotation-provider
spring.dubbo.registry.address=zookeeper://127.0.0.1:2181
spring.dubbo.protocol.name=dubbo
spring.dubbo.protocol.port=20880
spring.dubbo.scan=org.mingyu.study
```
在Spring Application的application.properties中添加spring.dubbo.scan即可支持Dubbo服务发布,其中scan表示要扫描的package目录
* spring boot启动
```
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.run(args);
    }
```
* 编写你的Dubbo服务,只需要添加要发布的服务实现上添加 @Service ,如下
```
@Service(version = "1.0.0")
public class ServiceImpl implements ServiceA {

    @Override
    public String test() {
        return "hello";
    }
}

```

##如何引用Dubbo服务
* 在Spring Boot项目的pom.xml中添加以下依赖:
```
 <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-dubbo</artifactId>
         <version>1.3.1.RELEASE</version>
 </dependency>
 ```
* 在application.properties添加Dubbo的版本信息和客户端超时信息,如下:
```
spring.dubbo.application.name=annotation-consumer
spring.dubbo.registry.address=zookeeper://127.0.0.1:2181
spring.dubbo.scan=com.lance
```
在Spring Application的application.properties中添加spring.dubbo.scan即可支持Dubbo服务发布,其中scan表示要扫描的package目录

* spring boot启动
```
@SpringBootApplication
public class TestMain {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.run(args);
    }
}
```
* 引用Dubbo服务,只需要添加要发布的服务实现上添加 @Reference ,如下:
```
@Component
public class BarAction {

    @Reference(version = "1.0.0")
    private ServiceA fooService;
}
```

##关于spring-boot开发知识
<a href ="http://www.jianshu.com/users/aa6df7dd83ec/latest_articles">spring-boot学习笔记</a>
