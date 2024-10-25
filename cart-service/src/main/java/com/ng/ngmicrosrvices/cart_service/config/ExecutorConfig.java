package com.ng.ngmicrosrvices.cart_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorConfig {

    @Bean
    ExecutorService executorService(){
        return Executors.newSingleThreadExecutor();
    }
}
