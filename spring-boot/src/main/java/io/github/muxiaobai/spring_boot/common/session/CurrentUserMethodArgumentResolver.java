package io.github.muxiaobai.spring_boot.common.session;

import io.github.muxiaobai.spring_boot.common.util.Constant;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;


public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(SessionUser.class) && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        SessionUser sessionUserInfo = (SessionUser) webRequest.getAttribute(Constant.CURRENT_USER, RequestAttributes.SCOPE_REQUEST);
        if (sessionUserInfo != null) {
            return sessionUserInfo;
        }
        throw new MissingServletRequestPartException(Constant.CURRENT_USER);
    }
}