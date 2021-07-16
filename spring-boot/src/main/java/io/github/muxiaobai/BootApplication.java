/**
 * Project Name:spring-boot
 * File Name:Application.java
 * Package Name:io.github.muxiaobai.spring_boot
 * Date:2019年3月21日下午7:13:52
 * Copyright (c) 2019, All Rights Reserved.
 */

package io.github.muxiaobai;
/**
 * ClassName:Application 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月21日 下午7:13:52 
 * @author Mu Xiaobai
 * @version
 * @since JDK 1.8
 */


import io.github.muxiaobai.common.config.InitCacheConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@EnableTransactionManagement
@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        FeignAutoConfiguration.class,})
public class BootApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }
    @GetMapping("url")
    public List<String> getAllUrl() {
        return initCacheConfig.getAllUrl();
    }

    @Autowired
    InitCacheConfig initCacheConfig;

}