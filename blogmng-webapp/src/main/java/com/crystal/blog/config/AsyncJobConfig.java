package com.crystal.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
* @Author: caoyue
* @Description: 同步job配置
* @Date: 18:58 2018/9/11
**/
@Configuration
@EnableAsync
public class AsyncJobConfig {
    /*
   此处成员变量应该使用@Value从配置中读取
    */
    private int corePoolSize = 5;
    private int maxPoolSize = 10;
    private int queueCapacity = 10;

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.initialize();
        return executor;
    }
}
