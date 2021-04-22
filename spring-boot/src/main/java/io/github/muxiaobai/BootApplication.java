/**
 * Project Name:spring-boot
 * File Name:Application.java
 * Package Name:io.github.muxiaobai.spring_boot
 * Date:2019年3月21日下午7:13:52
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.spring_boot;
/**
 * ClassName:Application 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月21日 下午7:13:52 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication(exclude= {DruidDataSourceAutoConfigure.class,DataSourceAutoConfiguration.class,
        FeignAutoConfiguration.class,})
public class BootApplication {
      public static void main(String[] args) {
          SpringApplication.run(Application.class, args);
      }
      
}