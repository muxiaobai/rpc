/**
 * Project Name:netty-zkp-server
 * File Name:RpcService.java
 * Package Name:cn.forp.netty_zkp_server
 * Date:2019年3月14日上午10:03:29
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package cn.forp.netty_zkp_server;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * ClassName:RpcService 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月14日 上午10:03:29 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component // 表明可被 Spring 扫描
public @interface RpcService {

    Class<?> value();
}