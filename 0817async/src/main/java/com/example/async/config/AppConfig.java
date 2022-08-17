package com.example.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

// Thread를 사용자가 직접 추가
@Configuration
public class AppConfig {

    @Bean("async-thread")
    public Executor asyncThread(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(100);  // 추가 공부 필요
        threadPoolTaskExecutor.setCorePoolSize(10);  // 추가 공부 필요
        threadPoolTaskExecutor.setQueueCapacity(10);  // 추가 공부 필요
        threadPoolTaskExecutor.setThreadNamePrefix("Async-");
        return threadPoolTaskExecutor;
    }

}
