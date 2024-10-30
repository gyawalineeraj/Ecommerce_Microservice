package com.ng.ngmicrosrvices.ng_ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableFeignClients
public class NgEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NgEcommerceApplication.class, args);
	}

}
