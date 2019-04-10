/**
 * Project Name:spring-boot
 * File Name:AbsLock.java
 * Package Name:io.github.muxiaobai.spring_boot.lock
 * Date:2019年4月10日上午11:42:18
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.spring_boot.lock;
/**
 * ClassName:AbsLock 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年4月10日 上午11:42:18 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public abstract  class AbsLock implements Lock {

    @Override
    public void lock() {
        if(getLock()){
            System.out.println("=========获取到分布式锁=========");
        }else{
            waitLock();
            
            getLock();
        }
    }
    
    public abstract Boolean getLock();
    public abstract void waitLock();

}

