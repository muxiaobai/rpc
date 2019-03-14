/**
 * Project Name:netty-rpc-api
 * File Name:h.java
 * Package Name:cn.forp.netty_rpc_api
 * Date:2019年3月13日下午5:37:15
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package cn.forp.netty_rpc_api;
/**
 * ClassName:h 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月13日 下午5:37:15 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */

public interface RpcService {
    String hello(String name);
    int add(int a,int b);
    int sub(int a,int b);
}