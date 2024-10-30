package com.ng.ngmicrosrvices.ng_ecommerce.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;

@Configuration
public class OAuthClienConfig {

    @Bean
    public OAuth2AuthorizedClientManager auth2AuthorizedClientManager(
            ClientRegistrationRepository registrationRepository,
            OAuth2AuthorizedClientRepository clientRepository){

        OAuth2AuthorizedClientProvider auth2AuthorizedClientProvider =
                OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();

        DefaultOAuth2AuthorizedClientManager auth2AuthorizedClientManager =
                new DefaultOAuth2AuthorizedClientManager(registrationRepository,
                        clientRepository);

        auth2AuthorizedClientManager.setAuthorizedClientProvider(auth2AuthorizedClientProvider);
        return auth2AuthorizedClientManager;
    }

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2AuthorizedClientManager manager){
        return requestTemplate -> {
             OAuth2AuthorizeRequest authRequest =
                     OAuth2AuthorizeRequest.withClientRegistrationId("client")
                             .principal("principal")
                             .build();
             OAuth2AuthorizedClient client = manager.authorize(authRequest);
             requestTemplate.header("Authorization",
                     "Bearer " + client.getAccessToken().getTokenValue());
        };
    }
}
