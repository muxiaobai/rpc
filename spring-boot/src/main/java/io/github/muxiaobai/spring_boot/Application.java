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


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
public class Application {
      public static void main(String[] args) {
          SpringApplication.run(Application.class, args);
      }
      
}