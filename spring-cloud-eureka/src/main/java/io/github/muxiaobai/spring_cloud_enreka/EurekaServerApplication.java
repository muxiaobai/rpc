package io.github.muxiaobai.spring_cloud_enreka;
/**
 * Project Name:spring-boot
 * File Name:Application.java
 * Package Name:io.github.muxiaobai.spring_boot
 * Date:2019年3月21日下午7:13:52
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {
      public static void main(String[] args) {
          SpringApplication.run(EurekaServerApplication.class, args);
//          new SpringApplicationBuilder(EurekaServerApplication.class).web(WebApplicationType.NONE).run(args);
      }
      
}