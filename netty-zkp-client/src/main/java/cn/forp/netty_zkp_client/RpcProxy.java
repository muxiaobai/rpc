/**
 * Project Name:netty-zkp-client
 * File Name:RpcProxy.java
 * Package Name:cn.forp.netty_zkp_client
 * Date:2019年3月14日上午10:10:23
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package cn.forp.netty_zkp_client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.forp.netty_zkp_common.RpcRequest;
import cn.forp.netty_zkp_common.RpcResponse;
import cn.forp.netty_zkp_zookeeper.ServiceDiscovery;

/**
 * ClassName:RpcProxy 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月14日 上午10:10:23 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public class RpcProxy {
    private static final Logger LOGGER = LoggerFactory.getLogger(RpcProxy.class);
    private String serverAddress;
    private ServiceDiscovery serviceDiscovery;

    public RpcProxy(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public RpcProxy(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    @SuppressWarnings("unchecked")
    public <T> T create(Class<?> interfaceClass) {
        return (T) Proxy.newProxyInstance(
            interfaceClass.getClassLoader(),
            new Class<?>[]{interfaceClass},
            new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    RpcRequest request = new RpcRequest(); // 创建并初始化 RPC 请求
                    request.setRequestId(UUID.randomUUID().toString());
                    request.setClassName(method.getDeclaringClass().getName());
                    request.setMethodName(method.getName());
                    request.setParameterTypes(method.getParameterTypes());
                    request.setParameters(args);

                    if (serviceDiscovery != null) {
                        String serviceName = interfaceClass.getName();
//                        if (StringUtil.isNotEmpty(serviceVersion)) {
//                            serviceName += "-" + serviceVersion;
//                        }
                        serverAddress = serviceDiscovery.discover(); // 发现服务
                        LOGGER.info("discover service: {} => {}", serviceName, serverAddress);
                    }

                    String[] array = serverAddress.split(":");
                    String host = array[0];
                    int port = Integer.parseInt(array[1]);

                    RpcClient client = new RpcClient(host, port); // 初始化 RPC 客户端
                    RpcResponse response = client.send(request); // 通过 RPC 客户端发送 RPC 请求并获取 RPC 响应

                    if (response.isError()) {
                        throw response.getError();
                    } else {
                        return response.getResult();
                    }
                }
            }
        );
    }
}