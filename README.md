# rpc
netty-zookeeper-rpc-demo

## io 实现rpc
##### 注:

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
