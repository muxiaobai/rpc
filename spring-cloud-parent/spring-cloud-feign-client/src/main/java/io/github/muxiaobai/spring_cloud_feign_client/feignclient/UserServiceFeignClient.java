/**
 * Project Name:spring-boot
 * File Name:UserController.java
 * Package Name:io.github.muxiaobai.spring_boot.manage.controller
 * Date:2019年4月8日下午7:58:02
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.spring_cloud_feign_client.feignclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

@Component
@FeignClient(value = "spring-cloud-service") //这里的name对应调用服务的spring.applicatoin.name
public interface UserServiceFeignClient {
    /*@RequestMapping(value = "/demo/{input}",method = RequestMethod.GET)
    public String demo(@PathVariable String input){
        return  input +",port:"+port+",Thread:"+Thread.currentThread().getName();
    }*/

    @RequestMapping(value = "/demo/{input}")
    String demo(@RequestParam("input") String input);
}
