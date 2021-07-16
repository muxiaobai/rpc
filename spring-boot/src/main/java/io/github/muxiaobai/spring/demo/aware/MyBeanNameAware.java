package io.github.muxiaobai.spring.demo.aware;

import io.github.muxiaobai.spring.demo.X;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyBeanNameAware implements BeanNameAware {
    @Autowired
    private X x;

    public MyBeanNameAware() {
        System.out.println("create MyBeanNameAware");
        System.out.println(x);
    }
    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware.setBeanName:" + name);
    }
}
