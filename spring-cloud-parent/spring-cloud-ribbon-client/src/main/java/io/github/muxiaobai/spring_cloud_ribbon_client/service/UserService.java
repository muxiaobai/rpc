package io.github.muxiaobai.spring_cloud_ribbon_client.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Zhang Pengfei
 * @version V1.0
 * @PROJECT_NAME rpc
 * @Package io.github.muxiaobai.spring_cloud_ribbon_client.service
 * @date 2019/5/30/030 10:26
 * @TODO TODO
 * @Copyright © 2000-2019  All Rights Reserved.
 */
@Service
public class UserService {
    @Autowired
    public RestTemplate restTemplate;

    private static  final String REST_URL_PREFIX= "http://spring-cloud-service";

    @HystrixCommand(fallbackMethod = "reqErrFallBack")
    public String queryOrderStatusById(String orderId) {
        return restTemplate.getForObject(REST_URL_PREFIX+"/queryOrderStatusById?orderId="+orderId,String.class);
    }

    @HystrixCommand(fallbackMethod = "reqErrFallBack")
    public String queryWlInfoByOrderId(String orderId) {
        //return restTemplate.getForObject("http://xxx/xxx?xxx="+xxx,String.class);
        return ":[订单-商品-已发货]";
    }

    @HystrixCommand(fallbackMethod = "reqErrFallBack")
    public String demo(String input) {
        return restTemplate.getForObject(REST_URL_PREFIX+"/demo/"+input,String.class);
        //return ":[订单-商品-已发货]";
    }

    public String reqErrFallBack(String orderId) {
        //记录调用日志
        return "记录熔断日志:Error:"+orderId+"调用失败调用IP:xxxx!!!";
    }

}
