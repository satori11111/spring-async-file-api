package com.example.springasyncapi.config;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean("executor")
    public Executor executor() {
        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        threadPoolExecutor.setCorePoolSize(2);
        threadPoolExecutor.setMaxPoolSize(2);
        threadPoolExecutor.setQueueCapacity(100);
        threadPoolExecutor.setQueueCapacity(100);
        threadPoolExecutor.initialize();
        return threadPoolExecutor;
    }
}
