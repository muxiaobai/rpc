package io.github.muxiaobai.spring_cloud_ribbon_client.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Zhang Pengfei
 * @version V1.0
 * @PROJECT_NAME rpc
 * @Package io.github.muxiaobai.spring_cloud_service.config
 * @date 2019/5/29/029 16:10
 * @TODO TODO
 * @Copyright © 2000-2019 . All Rights Reserved.
 */
@Configuration
public class RestConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
