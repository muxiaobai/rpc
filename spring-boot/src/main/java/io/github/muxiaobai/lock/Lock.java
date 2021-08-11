/**
 * Project Name:spring-boot
 * File Name:Lock.java
 * Package Name:io.github.muxiaobai.spring_boot.lock
 * Date:2019年4月10日上午11:41:23
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.lock;
/**
 * ClassName:Lock 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年4月10日 上午11:41:23 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public interface Lock {
    public void lock();
    public void unlock();
}

