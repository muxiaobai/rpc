package io.github.muxiaobai.spring_boot.common.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * 初始化缓存
 * @author
 * @date
 *
 */
@Slf4j
@Order(value=1)
@Component
public class InitCacheConfig implements CommandLineRunner {
	
    @Override
	public void run(String... args) {
	    //启动的时候加载一次， 之后获取数据的时候

		log.info("》》系统基础数据初始化开始》》》》》》》》》》》》》》》》》》》》》》》》》》》");

        log.info("》》系统基础数据初始化完毕》》》》》》》》》》》》》》》》》》》》》》》》》》》");
	}

}
