/**
 * Project Name:spring-boot
 * File Name:DemoService.java
 * Package Name:io.github.muxiaobai.spring_boot.service
 * Date:2019年3月21日下午7:31:58
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.manage.service;

import io.github.muxiaobai.manage.service.remoteService.RemoteServiceCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

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
public class DemoService extends SpringApplication {
    @Autowired
    private RemoteServiceCall remoteCall;
   
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> doRemote(String code) {
        Map<String, Object> result = remoteCall.getOne (code);
        return result;
    }
}

