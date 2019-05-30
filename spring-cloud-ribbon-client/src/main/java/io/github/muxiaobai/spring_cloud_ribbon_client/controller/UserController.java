/**
 * Project Name:spring-boot
 * File Name:UserController.java
 * Package Name:io.github.muxiaobai.spring_boot.controller
 * Date:2019年4月8日下午7:58:02
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.spring_cloud_ribbon_client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
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
    @Value("${server.port}")
    private String port;
    @Autowired
    public RestTemplate restTemplate;

    private static  final String REST_URL_PREFIX= "http://SPRING-CLOUD-SERVICE";
   /* @RequestMapping(value = "/demo",method = RequestMethod.GET)
    public String demo(@RequestParam String input){
        return  input +",port:"+port+",Thread:"+Thread.currentThread().getName();
    }*/

    @RequestMapping("/demo/{input}")
    public String demo(@PathVariable String input){
        System.out.println("dddd");
        return restTemplate.getForObject(REST_URL_PREFIX+"/demo"+input,String.class);
    }

}

