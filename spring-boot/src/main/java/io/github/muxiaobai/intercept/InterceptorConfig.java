package io.github.muxiaobai.intercept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    /**
     * 重写添加拦截器方法
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器，并制定顺序，顺序越小越先执行
        //前台搜索相关拦截器
        //防火墙
        registry.addInterceptor(getFireWallInterceptor())
                .addPathPatterns("/**")
                //排除前端地址
                .excludePathPatterns("/**", "/demo/**")
                .order(1);
        //搜索禁词
        registry.addInterceptor(getIpInterceptor())
                .addPathPatterns("/**")
                //排除前端地址
                .excludePathPatterns("/**", "/demo/**")
                .order(2);
    }
    @Bean
    public FireWallIntercept getFireWallInterceptor(){return new FireWallIntercept();}

    @Bean
    public IPIntercept getIpInterceptor(){return new IPIntercept();}
}
