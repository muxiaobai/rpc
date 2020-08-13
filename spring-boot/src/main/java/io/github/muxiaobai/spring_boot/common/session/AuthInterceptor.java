package io.github.muxiaobai.spring_boot.common.session;

import io.github.muxiaobai.spring_boot.common.util.Constant;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**

 * @version V1.0

 * @date 2019/6/19/019 13:43
*/
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object handler) throws Exception {

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        SessionUser sessionUser = new SessionUser();
        request.setAttribute(Constant.CURRENT_USER, sessionUser);
        return true;
    }
}
