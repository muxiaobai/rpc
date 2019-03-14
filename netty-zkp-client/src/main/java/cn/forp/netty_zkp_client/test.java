/**
 * Project Name:netty-zkp-client
 * File Name:test.java
 * Package Name:cn.forp.netty_zkp_client
 * Date:2019年3月14日上午10:29:55
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package cn.forp.netty_zkp_client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.forp.netty_zkp_api.HelloService;

/**
 * ClassName:test 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月14日 上午10:29:55 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public class test {
     @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext act = new ClassPathXmlApplicationContext("spring.xml");
        RpcProxy rpcProxy= act.getBean("rpcProxy",RpcProxy.class);
        HelloService helloService = rpcProxy.create(HelloService.class);
        String result = helloService.hello("World");
        System.out.print(result);
        System.exit(0);
     }
}

