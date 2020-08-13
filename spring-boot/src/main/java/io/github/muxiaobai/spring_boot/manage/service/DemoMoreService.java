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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.muxiaobai.spring_boot.manage.remoteService.RemoteServiceCall;

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
public class DemoMoreService {

    @Autowired
    private RemoteServiceCall remoteCall;
    
    LinkedBlockingQueue<Request> queue = new LinkedBlockingQueue<Request>();
    
    public Map<String, Object> doRemote(String orderCode) throws InterruptedException, ExecutionException{
        
        Request request = new Request();
        request.orderCode = orderCode;
        //jdk1.8
        CompletableFuture<Map<String, Object>> future = new CompletableFuture<>();
        request.future = future;
        //队列中
        queue.add(request);
        return future.get();//阻塞状态
    }
    /**
     * 定时任务 初始化执行
     * init:(). servlet init方法之前调用这个注解的方法，只会被调用一次
     * @author Mu Xiaobai
     * @since JDK 1.8
     */
    @PostConstruct
    public void init(){
        //定时任务两个线程数
        ScheduledExecutorService scheduledExecutorService =Executors.newScheduledThreadPool(2);
       
        scheduledExecutorService.scheduleAtFixedRate(()->{
           
            //run
            int size = queue.size();
            if(size == 0){
                return;
            }
            //弹出Request
            List<Request> requests = new ArrayList<>();
            for(int i=0; i<size;i++){
                Request request = queue.poll();//出队列
                requests.add(request);
            }
          
            System.out.println("10ms 取到的本地请求数："+size);
            
            //循环requests分离orderCode和future
            List<String> orderCodes = new ArrayList<>();
            for(Request request :requests){
                orderCodes.add(request.orderCode);
            }
            
            //查询返回结果
            List<Map<String,Object>> responses=remoteCall.getMore(orderCodes);
            System.out.println(responses);
            //分离返回内容
            Map<String,Map<String,Object>>  responseMap = new HashMap<>(); 
            for(Map<String,Object> response:responses){
                String orderCode = response.get("orderCode").toString();
                responseMap.put(orderCode, response);
            }
            //结果转发到request
            for(Request request:requests){
                Map<String, Object> result = responseMap.get(request.orderCode);
                request.future.complete(result);//转发到对应的线程
            }
            
        }, 0, 10, TimeUnit.MILLISECONDS); //10ms执行
        
    }
    class Request{
        String orderCode;
        CompletableFuture<Map<String, Object>> future;
    }
}

