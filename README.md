# rpc
netty-zookeeper-rpc-demo

## io 实现rpc
##### 注: 
- io-rpc-common

## 实现tomcat功能
##### 使用到servlet socket io
- io-tomcat 

## netty rpc  
##### 注：普通的使用netty实现rpc调用，使用java的反射

- netty-rpc-api
- netty-rpc-consumer
- netty-rpc-registry


## netty zookeeper Protostuff rpc
##### 注: 使用 Spring 提供依赖注入与参数配置，使用 Netty 实现 NIO 方式的数据传输，使用 Protostuff 实现对象序列化，使用 ZooKeeper 实现服务注册与发现。
- netty-zkp-api
- netty-zkp-common
- netty-zkp-zookeeper
- netty-zkp-server
- netty-zkp-client

按照顺序进行maven install
zookeeper需要创建一个永久节点 `create /registry ""`

## 实现对应的spring和Mybatis功能

- spring-my-demo 

## spring-boot
- spring-boot 实现spring-boot 连接mysql和Mybatis

## spring cloud

需要注意的是spring-boot和spring-cloud版本，需要匹配


|Spring Boot | Spring Cloud |
|:--:|:--:|
|1.2.x	|Angel版本|
|1.3.x	|Brixton版本|
|1.4.x	|Camden版本|
|1.5.x	|Dalston版本、Edgware版本|
|2.0.x	|Finchley版本|

- spring-cloud-parent 所有spring-cloud的引用
- spring-cloud-eureka 服务注册发现
- spring-cloud-service 服务
- spring-cloud-service-2 服务复本
- spring-cloud-ribbon-client ribbon服务调用端同时实现hy熔断机制
- spring-cloud-feign-client feign声明式服务调用