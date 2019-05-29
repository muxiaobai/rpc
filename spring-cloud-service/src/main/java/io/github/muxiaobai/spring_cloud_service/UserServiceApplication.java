package io.github.muxiaobai.spring_cloud_service;
/**
 * Project Name:spring-boot
 * File Name:Application.java
 * Package Name:io.github.muxiaobai.spring_cloud_service
 * Date:2019年3月21日下午7:13:52
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class UserServiceApplication {
      public static void main(String[] args) {
          new SpringApplicationBuilder(UserServiceApplication.class).web(WebApplicationType.NONE).run(args);
      }
}