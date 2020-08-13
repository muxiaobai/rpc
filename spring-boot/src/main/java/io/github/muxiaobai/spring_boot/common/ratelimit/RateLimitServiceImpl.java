package io.github.muxiaobai.spring_boot.common.ratelimit;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**

 * @version V1.0

 * @date 2020/1/7/007 16:35
 * @ Guava RateLimiter的限流实现
*/
@Service
public class RateLimitServiceImpl implements RateLimitService {
    private RateLimitConfig config;
    private RateLimiter rateLimiter;
    @Autowired
    public RateLimitServiceImpl(RateLimitConfig config) {
        this.config = config;
        this.rateLimiter = RateLimiter.create(this.config.getPermitsPerSecond());
    }
    @Override
    public boolean tryAcquire() {
        return this.tryAcquire(1);
    }
    @Override
    public boolean tryAcquire(int permits) {
        return rateLimiter.tryAcquire(permits);
    }
    @Override
    public boolean acquire(long timeout) {
        return this.acquire(1, timeout);
    }
    @Override
    public boolean acquire(int permits, long timeout) {
        long start = System.currentTimeMillis();
        for (;;) {
            boolean tryAcquire = rateLimiter.tryAcquire(permits);
            if (tryAcquire) {
                return true;
            }
            long end = System.currentTimeMillis();
            if ((end - start) >= timeout) {
                return false;
            }
        }
    }
}