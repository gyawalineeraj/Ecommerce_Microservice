package com.ng.ngmicrosrvices.ng_ecommerce.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityFilterChainConfig {

    private final CustomJwtConverter customJwtConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(c -> c.disable());
        http.csrf(c -> c.disable());
        http.authorizeHttpRequests(c -> c
                        .requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers("/vendor/**").hasRole("VENDOR")
                        .anyRequest().authenticated());

        http.oauth2ResourceServer(c -> c
                .jwt(j -> j.jwtAuthenticationConverter(customJwtConverter)));
        return http.build();

    }
}
