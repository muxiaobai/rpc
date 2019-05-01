package io.github.muxiaobai.spring_my_demo.demo.service;

import io.github.muxiaobai.spring_my_demo.mvcframework.annotation.Service;

@Service
public class OneService {
    public  String retName(String name){
        return  "gggg"+name;
    }

}
