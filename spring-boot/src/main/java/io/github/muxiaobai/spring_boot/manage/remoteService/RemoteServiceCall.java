/**
 * Project Name:spring-boot
 * File Name:remoteServiceCall.java
 * Package Name:io.github.muxiaobai.spring_boot.manage.remoteService
 * Date:2019年3月22日上午10:37:05
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.spring_boot.manage.remoteService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * ClassName:remoteServiceCall 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月22日 上午10:37:05 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
@Service
public class RemoteServiceCall {
    public Map<String,Object> getOne(String orderCode){
        Map<String,Object> map = new HashMap<>();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        }
        map.put("orderCode", orderCode);
        map.put("hello", "hello");
        return map ;
        
    }
    public Map<String,Object> getTwo(String orderCode){
        Map<String,Object> map = new HashMap<>();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        }
        map.put("orderCode2", orderCode);
        map.put("hello2", "hello2");
        return map ;
        
    }
    public List<Map<String,Object>> getMore(List<String> orderCodes){
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        for(String orderCode: orderCodes){
            Map<String,Object> map = new HashMap<>();
            map.put("code", orderCode);
            map.put("hello", "hello");
            list.add(map);
        }
        return list ;
        
    }
}

