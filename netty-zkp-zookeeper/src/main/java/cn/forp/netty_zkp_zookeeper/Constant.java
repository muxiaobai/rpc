/**
 * Project Name:netty-zkp-server
 * File Name:Constant.java
 * Package Name:cn.forp.netty_zkp_server
 * Date:2019年3月14日上午9:44:21
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package cn.forp.netty_zkp_zookeeper;
/**
 * ClassName:Constant 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月14日 上午9:44:21 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public interface Constant {

    int ZK_SESSION_TIMEOUT = 5000;

    String ZK_REGISTRY_PATH = "/registry";
    String ZK_DATA_PATH = ZK_REGISTRY_PATH + "/data";
}