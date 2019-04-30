/**
 * Project Name:spring-boot
 * File Name:DemoService.java
 * Package Name:io.github.muxiaobai.spring_boot.service
 * Date:2019年3月21日下午7:31:58
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.spring_boot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.muxiaobai.spring_boot.remoteService.RemoteServiceCall;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName:DemoService 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月21日 下午7:31:58 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */

@Service
public class DemoMoreThreadService {

    @Autowired
    private RemoteServiceCall remoteCall;
    
    protected static ExecutorService threads = Executors.newFixedThreadPool(10);
    /**
     * 使用线程池并行
     * doExecPoolRemote:().
     * @author Mu Xiaobai
     * @param orderCode
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @since JDK 1.8
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> doExecPoolRemote(String orderCode) throws InterruptedException, ExecutionException{
        System.out.println("sssss");
        Callable<Map<String, Object>> callable =  new Callable<Map<String, Object>>() {
            @Override
            public Map<String, Object> call() throws Exception {
                return remoteCall.getOne(orderCode);
            }
        }; 
      
        Callable<Map<String, Object>> callable1 =  new Callable<Map<String, Object>>() {
            @Override
            public Map<String, Object> call() throws Exception {
                return remoteCall.getTwo(orderCode);
            }
        }; 
        FutureTask<Map<String, Object>> futureTask = new FutureTask<>(callable);
        FutureTask<Map<String, Object>> futureTask1 = new FutureTask<>(callable1);
        
        threads.submit(futureTask);
        threads.submit(futureTask1);
        
        Map<String, Object> result = new HashMap<>();
        result.putAll(futureTask.get());
        result.putAll(futureTask1.get());
        return result;
}
    /**
     * 使用线程并行
     * doThreadRemote:().
     * @author Mu Xiaobai
     * @param orderCode
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @since JDK 1.8
     */
    public Map<String, Object> doThreadRemote(String orderCode) throws InterruptedException, ExecutionException{
        
        Callable<Map<String, Object>> callable =  new Callable<Map<String, Object>>() {
            @Override
            public Map<String, Object> call() throws Exception {
                return remoteCall.getOne(orderCode);
            }
        }; 
        
        Callable<Map<String, Object>> callable1 =  new Callable<Map<String, Object>>() {
            @Override
            public Map<String, Object> call() throws Exception {
                return remoteCall.getTwo(orderCode);
            }
        }; 
        
        FutureTask<Map<String, Object>> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        FutureTask<Map<String, Object>> futureTask1 = new FutureTask<>(callable1);
        new Thread(futureTask1).start();
        
        Map<String, Object> result = new HashMap<>();
        result.putAll(futureTask.get());
        result.putAll(futureTask1.get());
        return result;
    }
    /**
     * 串行
     * doEachRemote:().
     * @author Mu Xiaobai
     * @param orderCode
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @since JDK 1.8
     */
    public Map<String, Object> doEachRemote(String orderCode) throws InterruptedException, ExecutionException{
       
        Map<String, Object> result = new HashMap<>();
        result.putAll(remoteCall.getOne(orderCode));
        result.putAll(remoteCall.getTwo(orderCode));
       
        return result;
    }
 
}

