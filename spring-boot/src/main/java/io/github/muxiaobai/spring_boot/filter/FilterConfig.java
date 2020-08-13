package io.github.muxiaobai.spring_boot.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Autowired
    private DemoFilter1 demoFilter;
    @Autowired
    private DemoFilter2 demoFilter2;


    @Bean
    public FilterRegistrationBean registerAuthFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(demoFilter);
        registration.addUrlPatterns("/*");
        registration.setName("authFilter");
        registration.setOrder(11);
        ///值越小，Filter越靠前。
        return registration;
    }
    @Bean
    public FilterRegistrationBean registerAuthFilter2() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(demoFilter2);
        registration.addUrlPatterns("/*");
        registration.setName("authFilter1");
        registration.setOrder(12);
        ///值越小，Filter越靠前。
        return registration;
    }
    //如果有多个Filter，再写一个public FilterRegistrationBean registerOtherFilter(){...}即可。
}