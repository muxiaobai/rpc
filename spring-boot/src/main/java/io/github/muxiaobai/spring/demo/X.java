package io.github.muxiaobai.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class X {
    @Autowired
    private Y y;
    public  X(){
        System.out.println("create X");
        System.out.println(y);
    }
    public void say() {
        System.out.println("I'm X;");
    }

    public Y getY() {
        return y;
    }
}
