package io.github.muxiaobai.common.redis;


import io.github.muxiaobai.common.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


/**
 * 初始化缓存
 *
 * @author
 * @date
 */
@Slf4j
@Order(value = 1)
@Component
public class InitCacheConfig implements CommandLineRunner {

    private RList list;

    @Autowired
    private RedissonClient redissonClient;

    private void init() {
        list = redissonClient.getList(Constant.VERIFY_REDIS_KEY);
        list.expire(2, TimeUnit.MINUTES);
        list.addAll(new ArrayList());
    }


    @Override
    public void run(String... args) {
        //启动的时候加载一次， 之后获取数据的时候

        log.info("》》系统基础数据初始化开始》》》》》》》》》》》》》》》》》》》》》》》》》》》");
        init();
        log.info("》》系统基础数据初始化完毕》》》》》》》》》》》》》》》》》》》》》》》》》》》");
    }

}
