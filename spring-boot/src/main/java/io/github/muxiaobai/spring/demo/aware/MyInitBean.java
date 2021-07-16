package io.github.muxiaobai.spring.demo.aware;

import io.github.muxiaobai.spring.demo.X;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyInitBean implements InitializingBean {
    @Autowired
    private X x;

    public MyInitBean() {
        System.out.println("create MyInitBean");
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
