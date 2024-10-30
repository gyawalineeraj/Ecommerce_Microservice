package com.ng.ngmicrosrvices.ng_ecommerce.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic deleteProductTopic(){
        return TopicBuilder
                .name("delete-product")
                .partitions(3)
                .build();
    }
    @Bean
    public NewTopic addProductTopic(){
        return TopicBuilder
                .name("add-product")
                .partitions(3)
                .build();
    }
}
