/**
 * Project Name:netty-rpc-registry
 * File Name:Server.java
 * Package Name:cn.forp.netty_rpc_registry
 * Date:2019年3月13日下午5:59:57
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package cn.forp.netty_rpc_registry;

import io.github.muxiaobai.netty_rpc_api.RpcRegistry;

/**
 * ClassName:Server 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月13日 下午5:59:57 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public class Server {
    public static void main( String[] args ){
        new RpcRegistry(8080,"cn.forp.netty_rpc_registry").start();
    }
}

