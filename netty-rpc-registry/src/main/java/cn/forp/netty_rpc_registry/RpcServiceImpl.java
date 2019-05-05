/**
 * Project Name:netty-rpc-registry
 * File Name:RpcServiceImpl.java
 * Package Name:cn.forp.netty_rpc_registry
 * Date:2019年3月13日下午5:44:49
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package cn.forp.netty_rpc_registry;

import io.github.muxiaobai.netty_rpc_api.RpcService;

/**
 * ClassName:RpcServiceImpl 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月13日 下午5:44:49 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */

 
public class RpcServiceImpl implements RpcService{
    @Override
    public String hello(String name) {
        return "hello,my name is "+name;
    }
    @Override
    public int add(int a, int b) {
        return a + b;
    }
    @Override
    public int sub(int a, int b) {
        return a - b;
    }
}