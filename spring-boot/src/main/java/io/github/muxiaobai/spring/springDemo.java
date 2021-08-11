package io.github.muxiaobai.spring;

import io.github.muxiaobai.spring.demo.AppConfig;
import io.github.muxiaobai.spring.demo.X;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class springDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext  applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//        ac.setAllowCircularReferences(false);
        X x  = applicationContext.getBean(X.class);
        x.getY().say();
//        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        //打印spring容器当中所有bean的bd
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//            System.out.println(applicationContext.getBeanDefinition(beanDefinitionName));
//        }
    }
}
