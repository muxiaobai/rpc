/**
 * Project Name:spring-boot
 * File Name:UserController.java
 * Package Name:io.github.muxiaobai.spring_boot.controller
 * Date:2019年4月8日下午7:58:02
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.spring_cloud_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * ClassName:UserController
 * Function: TODO
 * Reason:	 TODO
 * Date:     2019年4月8日 下午7:58:02
 * @author   Mu Xiaobai
 * @version
 * @since    JDK 1.8
 */
@RestController
@RequestMapping
public class UserController {
    @Autowired
    public RestTemplate restTemplate;

    private static  final String REST_URL_PREFIX= "";
    @RequestMapping("/demo")
    public String demo(@RequestParam String input){
        return  input +",Thread:"+Thread.currentThread().getName();
    }

    @RequestMapping("/list")
    public List list(){
        return restTemplate.getForObject(REST_URL_PREFIX+"",List.class);
    }
}

