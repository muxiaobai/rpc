package io.github.muxiaobai.spring_boot;

import io.github.muxiaobai.spring_boot.remoteService.RemoteServiceCall;
import io.github.muxiaobai.spring_boot.service.DemoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        AnnotationConfigApplicationContext  applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(RemoteServiceCall.class);
        applicationContext.register(DemoService.class);
        applicationContext.refresh();
//        WebApplicationContextUtils.getWebApplicationContext (getServletContext ());
        DemoService demoService = (DemoService) applicationContext.getBean("demoService");
        System.out.println(demoService.doRemote("hello"));
    }
}
