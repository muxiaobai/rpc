/**
 * Project Name:spring-boot
 * File Name:Application.java
 * Package Name:io.github.muxiaobai.spring_boot
 * Date:2019年3月21日下午7:13:52
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.spring_boot;
/**
 * ClassName:Application 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月21日 下午7:13:52 
 * @author   Mu Xiaobai
 * @version  3-20-9_百万并发性能优化实战
 * @since    JDK 1.8	 
 */


import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.github.muxiaobai.spring_boot.service.DemoMoreService;
import io.github.muxiaobai.spring_boot.service.DemoMoreThreadService;
import io.github.muxiaobai.spring_boot.service.DemoService;

@SpringBootTest(classes= Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestThreadApplication {

    private static final int nums = 1000;
    private CountDownLatch countDownLatch = new CountDownLatch(nums); 
    @Autowired
    public DemoService demoService;
    @Autowired
    public DemoMoreService demoMoreService;
    @Autowired
    public DemoMoreThreadService demoMoreThreadService;
    
    @Test
    public void test(){
       
        for(int i  = 0;i<nums;i++){
            Thread thread = new Thread(()->{
                try {
                    countDownLatch.await();
//                    Map<String, Object> resMap= demoService.doRemote("123");
                    Long startTime =System.currentTimeMillis();
//                    Map<String, Object> resMap= demoMoreThreadService.doEachRemote(Thread.currentThread().getName());//依次调用
//                    Map<String, Object> resMap= demoMoreThreadService.doThreadRemote(Thread.currentThread().getName());//线程调用
                    Map<String, Object> resMap= demoMoreThreadService.doExecPoolRemote(Thread.currentThread().getName());//线程池调用
                    Long endTime = System.currentTimeMillis();
                    System.out.println("ThreadName:"+Thread.currentThread().getName()+",result:"+resMap+",endTime-startTime:"+(endTime-startTime)+"ms");
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
        }
        
        
    }
}