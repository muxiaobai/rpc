/**
 * Project Name:spring-boot
 * File Name:RedisLock.java
 * Package Name:io.github.muxiaobai.spring_boot.lock
 * Date:2019年4月10日上午11:45:09
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.lock;

import io.lettuce.core.RedisClient;

/**
 * ClassName:RedisLock 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年4月10日 上午11:45:09 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public class RedisLock extends AbsLock{
    private RedisClient RedisClient;
    @Override
    public  Boolean getLock() {
        return null;
    };
    @Override
    public void waitLock() {
        
    }
    @Override
    public void unlock(){
        
    }
}

