/**
 * Project Name:netty-zkp-server
 * File Name:HelloServiceImpl.java
 * Package Name:cn.forp.netty_zkp_server
 * Date:2019年3月14日上午10:06:08
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package cn.forp.netty_zkp_server;

import cn.forp.netty_zkp_api.HelloService;

/**
 * ClassName:HelloServiceImpl 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月14日 上午10:06:08 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
@RpcService(HelloService.class) // 指定远程接口
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello! " + name;
    }
}