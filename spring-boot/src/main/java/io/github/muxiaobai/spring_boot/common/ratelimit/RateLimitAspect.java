package io.github.muxiaobai.spring_boot.common.ratelimit;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**

 * @version V1.0

 * @date 2020/1/7/007 16:36
 * @ 限流切面
*/
@Slf4j
@Aspect
@Order(-1)
@Component
public class RateLimitAspect {
    private RateLimitConfig config;
    private RateLimitService rateLimitService;
    @Autowired
    public RateLimitAspect(RateLimitConfig config, RateLimitService rateLimitService) {
        this.config = config;
        this.rateLimitService = rateLimitService;
    }
    @Pointcut("execution(public * io.github.muxiaobai.spring_boot.manage.controller.*Controller.*(..))")
    public void executionMethod() {}
    @Around(value = "executionMethod()")
    public Object doRateLimit(ProceedingJoinPoint pjp) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("进入限流处理切面!");
        }
        Object result = null;
        // 判断是否限流
        try {
            if (config.isDoRateLimit()) {
                // 开启限流
                boolean acquireResult = false;
                // 1.查看是否配置超时时间
                if (config.getWaitTimeout() == 0L) {
                    // 2.获取令牌
                    acquireResult = rateLimitService.tryAcquire();
                } else {
                    // 2.获取令牌，超时时间内获取令牌
                    acquireResult = rateLimitService.acquire(config.getWaitTimeout());
                }
                if (acquireResult) {
                    // 3.成功获取令牌，放行
                    result = pjp.proceed();
                } else {
                    // 3.失败获取令牌，返回错误码 429 => to many requests
                    result = ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
                }
            } else {
                // 无开启限流，直接放行
                result = pjp.proceed();
            }
        } catch (Throwable e) {
            throw e;
        }
        if (log.isDebugEnabled()) {
            log.debug("限流处理切面结束!");
        }
        return result;
    }
}