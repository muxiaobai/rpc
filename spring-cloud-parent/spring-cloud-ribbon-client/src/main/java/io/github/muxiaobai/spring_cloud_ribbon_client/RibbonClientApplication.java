package io.github.muxiaobai.spring_cloud_ribbon_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**

 * @version V1.0
 * @PROJECT_NAME
 * @Package io.github.muxiaobai.spring_cloud_ribbon_client
 * @date 2019/5/30/030 9:16
 * @TODO TODO
 * @Copyright © 2000-2019 . All Rights Reserved.
 */
@RibbonClient(name = "spring-cloud-service")
@EnableHystrix
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class RibbonClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(RibbonClientApplication.class,args);
    }
}
