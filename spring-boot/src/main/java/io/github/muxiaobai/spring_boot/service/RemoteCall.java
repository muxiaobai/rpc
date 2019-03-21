/**
 * Project Name:spring-boot
 * File Name:RemoteCall.java
 * Package Name:io.github.muxiaobai.spring_boot.service
 * Date:2019年3月21日下午7:32:47
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.spring_boot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * ClassName:RemoteCall 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月21日 下午7:32:47 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
@Service
public class RemoteCall {
    public Map<String,Object> getOne(String code){
        Map<String,Object> map = new HashMap<>();
        map.put("code", code);
        map.put("hello", "hello");
        return map ;
        
    }
    public List<Map<String,Object>> getMore(String code){
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<>();
        map.put("code", code);
        map.put("hello", "hello");
        list.add(map);
        return list ;
        
    }
}

