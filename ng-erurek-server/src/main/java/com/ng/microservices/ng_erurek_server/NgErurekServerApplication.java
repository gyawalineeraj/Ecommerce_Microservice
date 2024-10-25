package com.ng.microservices.ng_erurek_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NgErurekServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NgErurekServerApplication.class, args);
	}

}
