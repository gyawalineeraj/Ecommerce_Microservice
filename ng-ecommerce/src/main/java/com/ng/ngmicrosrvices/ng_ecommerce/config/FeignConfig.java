package com.ng.ngmicrosrvices.ng_ecommerce.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        System.out.println("hello");

        return Logger.Level.FULL;
    }
}