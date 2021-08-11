package io.github.muxiaobai.spring.demo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Y implements InitializingBean {
    @Autowired
    private X x;

    public Y() {
        System.out.println("create Y");
        System.out.println(x);
    }

    public void say() {
        System.out.println("I'm Y;");
    }

    public X getX() {
        return x;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean.afterPropertiesSet x:" + x);
    }
}
