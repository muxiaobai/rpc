/**
 * Project Name:spring-boot
 * File Name:UserController.java
 * Package Name:io.github.muxiaobai.spring_boot.manage.controller
 * Date:2019年4月8日下午7:58:02
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.spring_cloud_feign_client.controller;

import io.github.muxiaobai.spring_cloud_feign_client.feignclient.UserServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    private static  final String REST_URL_PREFIX= "";
    /*@RequestMapping(value = "/demo/{input}",method = RequestMethod.GET)
    public String demo(@PathVariable String input){
        return  input +",port:"+port+",Thread:"+Thread.currentThread().getName();
    }*/

    @Autowired
    private UserServiceFeignClient userServiceFeignClient;

    @RequestMapping("/demo/{input}")
    public String hi(@PathVariable String input){
        return userServiceFeignClient.demo(input);
    }
}

