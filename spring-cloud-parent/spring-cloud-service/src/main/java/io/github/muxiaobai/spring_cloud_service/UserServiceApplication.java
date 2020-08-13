package io.github.muxiaobai.spring_cloud_service;
/**
 * Project Name:spring-boot
 * File Name:Application.java
 * Package Name:io.github.muxiaobai.spring_cloud_service
 * Date:2019年3月21日下午7:13:52
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class UserServiceApplication {
      public static void main(String[] args) {
          SpringApplication.run(UserServiceApplication.class,args);
      }
}