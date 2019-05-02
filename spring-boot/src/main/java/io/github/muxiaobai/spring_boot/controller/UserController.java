/**
 * Project Name:spring-boot
 * File Name:UserController.java
 * Package Name:io.github.muxiaobai.spring_boot.controller
 * Date:2019年4月8日下午7:58:02
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.spring_boot.controller;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import io.github.muxiaobai.spring_boot.service.DemoDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.muxiaobai.spring_boot.service.DemoMoreThreadService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * ClassName:UserController
 * Function: TODO
 * Reason:	 TODO
 * Date:     2019年4月8日 下午7:58:02
 * @author   Mu Xiaobai
 * @version
 * @since    JDK 1.8
 */
@Controller
@RequestMapping
@EnableSwagger2
public class UserController {
    @Autowired
    public DemoMoreThreadService demoMoreThreadService;
    @Autowired
    public DemoDBService demoDBService;
    /**
     *
     * getUser:().
     * @author Mu Xiaobai
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @since JDK 1.8
     */
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUser() throws InterruptedException, ExecutionException{
        Long startTime =System.currentTimeMillis();
//      Map<String, Object> resMap= demoMoreThreadService.doEachRemote(Thread.currentThread().getName());//依次调用
//      Map<String, Object> resMap= demoMoreThreadService.doThreadRemote(Thread.currentThread().getName());//线程调用
      Map<String, Object> resMap= demoMoreThreadService.doExecPoolRemote(Thread.currentThread().getName());//线程池调用

      Long endTime = System.currentTimeMillis();
      System.out.println("ThreadName:"+Thread.currentThread().getName()+",result:"+resMap);
      System.out.println("endTime-startTime:"+(endTime-startTime)+"ms");

      return resMap;

    }
    @RequestMapping(value = "/db",method = RequestMethod.GET)
    @ResponseBody
    public String getDb(@RequestParam("code") String code) throws InterruptedException, ExecutionException{
       return demoDBService.dbConn(code);
    }

}
