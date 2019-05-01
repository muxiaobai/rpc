package io.github.muxiaobai.spring_my_demo.demo.controller;

import io.github.muxiaobai.spring_my_demo.demo.service.OneService;
import io.github.muxiaobai.spring_my_demo.mvcframework.annotation.Autowried;
import io.github.muxiaobai.spring_my_demo.mvcframework.annotation.Controller;
import io.github.muxiaobai.spring_my_demo.mvcframework.annotation.RequestMapping;
import io.github.muxiaobai.spring_my_demo.mvcframework.annotation.RequestParam;
import io.github.muxiaobai.spring_my_demo.demo.service.DemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/hello")
public class DemoController {
    @Autowried
    private DemoService demoService;
    @Autowried
    private OneService oneService;
    @RequestMapping("/do")
    public  void  query(HttpServletRequest request, HttpServletResponse response, @RequestParam("name") String name){
        try {
            response.getWriter().write(demoService.retName(name));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @RequestMapping("/do2")
    public  void  query2(HttpServletRequest request, HttpServletResponse response, @RequestParam("name") String name){
        try {
            response.getWriter().write(demoService.retName(name));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
