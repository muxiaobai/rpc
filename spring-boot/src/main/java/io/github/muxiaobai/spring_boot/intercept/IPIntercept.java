package io.github.muxiaobai.spring_boot.intercept;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: manage
 * @description: 禁用词拦截器
 * @author: zhangliang
 * @create: 2020-01-06 13:09
 **/
@Slf4j
@Component
public class IPIntercept implements HandlerInterceptor {
    /**
     * controller方法调用前调用。
     *
     * @param request
     * @param response
     * @param handler
     * @return 往下执行则返回true，否则返回false
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("search history...........");
        return true;
    }

    /**
     * controller方法调用后视图渲染前执行。
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    /**
     * controller方法调用且视图渲染完成后执行
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {

    }
}
