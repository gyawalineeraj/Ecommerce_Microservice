package com.ng.ngmicrosrvices.ng_ecommerce.config;

import io.github.resilience4j.retry.RetryRegistry;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RetryRegistryEvenet {

    @Autowired
    private RetryRegistry registry;

    @PostConstruct
    public void postcon(){
        registry.retry("product").getEventPublisher().onRetry(ev -> log.info("Retyr is " +
                "being done " + ev));
    }
}
