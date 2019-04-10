/**
 * Project Name:spring-boot
 * File Name:TestLock.java
 * Package Name:io.github.muxiaobai.spring_boot.lock
 * Date:2019年4月10日下午3:31:36
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.spring_boot.lock;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.github.muxiaobai.spring_boot.Application;
import io.github.muxiaobai.spring_boot.service.DemoMoreService;
import io.github.muxiaobai.spring_boot.service.DemoService;

/**
 * ClassName:TestLock 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年4月10日 下午3:31:36 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */

@SpringBootTest(classes= Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestLock {
    private static final int nums = 1000;
    private CountDownLatch countDownLatch = new CountDownLatch(nums); 
    private int i =0;
    @Test
    public void test(){
        for(int i  = 0;i<nums;i++){
            Thread thread = new Thread(()->{
                try {
                    countDownLatch.await();
                    System.out.println("ThreadName:"+Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
              
            });
            thread.start();
            countDownLatch.countDown();
        }
        
        try {
            Thread.currentThread().sleep(10000);
        } catch (InterruptedException e) {
            
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        }
        
    }   
}