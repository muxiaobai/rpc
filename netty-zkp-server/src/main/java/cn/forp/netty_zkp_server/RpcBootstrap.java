/**
 * Project Name:netty-zkp-server
 * File Name:RpcBootstrap.java
 * Package Name:cn.forp.netty_zkp_server
 * Date:2019年3月14日上午9:28:51
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package cn.forp.netty_zkp_server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName:RpcBootstrap 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月14日 上午9:28:51 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public class RpcBootstrap {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("spring.xml");
    }
}