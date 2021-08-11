package io.github.muxiaobai.common.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 初始化缓存
 * @author
 * @date
 *
 */
@Slf4j
@Order(value=1)
@Component
public class InitCacheConfig implements CommandLineRunner {
    public List getAllUrl(){
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        //获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        List<String> urls = new ArrayList<>();
        for (RequestMappingInfo info : map.keySet()) {
            HandlerMethod handlerMethod = map.get(info);
            System.out.println(handlerMethod);

            //获取url的Set集合，一个方法可能对应多个url
            Set<String> patterns = info.getPatternsCondition().getPatterns();
            urls.addAll(patterns);
            System.out.println(patterns);
            // 这里可获取请求方式 Get,Post等等
            Set<RequestMethod> methods = info.getMethodsCondition().getMethods();
            for (RequestMethod requestMethod : methods) {
                System.out.println(requestMethod.name());
            }

        }
        System.out.println(urls);
        return  urls;
    }
    @Autowired
    WebApplicationContext applicationContext;
    @Override
	public void run(String... args) {
	    //启动的时候加载一次， 之后获取数据的时候

		log.info("》》系统基础数据初始化开始》》》》》》》》》》》》》》》》》》》》》》》》》》》");
        getAllUrl();
        log.info("》》系统基础数据初始化完毕》》》》》》》》》》》》》》》》》》》》》》》》》》》");
	}

}
