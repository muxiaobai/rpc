package io.github.muxiaobai.spring.demo.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("MyInstantiationAwareBeanPostProcessor,postProcessBeforeInstantiation：" + beanClass + ":" + beanName);
        return null;
    }

//    @Override
//    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
//        System.out.println("MyInstantiationAwareBeanPostProcessor,postProcessAfterInstantiation：" + bean.getClass() + ":" + beanName);
//        return false;
//    }
//    @Override
//    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
//        System.out.println("MyInstantiationAwareBeanPostProcessor,postProcessProperties：" + bean.getClass() + ":" + beanName);
//        return pvs;
//    }
}
