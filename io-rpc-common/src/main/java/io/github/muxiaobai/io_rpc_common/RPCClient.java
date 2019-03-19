/**
 * Project Name:ProjectTest
 * File Name:RPCClient.java
 * Package Name:RPC.HelloWorld
 * Date:2019年3月13日下午6:59:36
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.io_rpc_common;

import java.net.InetSocketAddress;

import io.github.muxiaobai.io_rpc_common.EchoService;
import io.github.muxiaobai.io_rpc_common.EchoServiceImpl;
import io.github.muxiaobai.io_rpc_common.RPCImporter;

/**
 * ClassName:RPCClient 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月13日 下午6:59:36 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public class RPCClient {
    public static void main(String[] args) {
        RPCImporter<EchoService> importer=new RPCImporter<EchoService>();
        System.out.println(EchoService.class.getName());
        
        EchoService echoService=importer.importer(EchoServiceImpl.class,new InetSocketAddress("localhost",8080));
        System.out.println(echoService.echo("hello"));
    }
}

