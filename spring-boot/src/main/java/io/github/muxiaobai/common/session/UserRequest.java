package io.github.muxiaobai.common.session;

import io.github.muxiaobai.common.util.Constant;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**

 * @version V1.0
 * @date
 * @获取当前请求session
*/
public class UserRequest {
        public static SessionUser getCurrentUser(){
            SessionUser user = null ;
            if(RequestContextHolder.getRequestAttributes()!=null&&((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()!=null){
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
             user = (SessionUser)request.getAttribute(Constant.CURRENT_USER);
            }
            return user;
        }
}
