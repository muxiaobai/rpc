package io.github.muxiaobai.common.session;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**

 * @version V1.0
 * @date 2019/6/19/019 13:41
*/
@Configuration
public class AppInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器//用户信息
        registry.addInterceptor(getAuthInterceptor()).addPathPatterns("/search/**").excludePathPatterns("**/swagger-ui.html");
    }

    @Bean
    public AuthInterceptor getAuthInterceptor(){return new AuthInterceptor();}

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver());
//        super.addArgumentResolvers(argumentResolvers);
    }

    @Bean
    public CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver() {
        return new CurrentUserMethodArgumentResolver();
    }

}