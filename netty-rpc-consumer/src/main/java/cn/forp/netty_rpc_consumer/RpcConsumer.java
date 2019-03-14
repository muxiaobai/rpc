/**
 * Project Name:netty-rpc-consumer
 * File Name:RpcConsumer.java
 * Package Name:cn.forp.netty_rpc_consumer
 * Date:2019年3月13日下午5:48:39
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package cn.forp.netty_rpc_consumer;

import cn.forp.netty_rpc_api.RpcService;

/**
 * ClassName:RpcConsumer 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月13日 下午5:48:39 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
 
public class RpcConsumer {
    public static void main( String[] args ){
        RpcService service = RpcProxy.create(RpcService.class);
        String hello = service.hello("rpc");
        System.out.println(hello);
        System.out.println(service.add(5, 3));
        System.out.println(service.sub(5, 3));
    }
}