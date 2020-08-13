package io.github.muxiaobai.spring_boot.common.ratelimit;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**

 * @version V1.0

 * @date 2020/1/7/007 16:33
 * @ 限流配置信息
*/

@Data
@Component
@ConfigurationProperties(prefix = "ratelimit")
public class RateLimitConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    private boolean           doRateLimit      = false;
    private long              waitTimeout;
    private long              permitsPerSecond;
}