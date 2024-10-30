package com.ng.ngmicrosrvices.ng_ecommerce.config;


import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Component
public class CustomJwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<SimpleGrantedAuthority> roles = extractAuthorities(jwt);
        return new JwtAuthenticationToken(jwt,roles);
    }

    private Collection<SimpleGrantedAuthority> extractAuthorities(Jwt jwt) {

        if (jwt.getClaimAsMap("realm_access") != null) {
            Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
            var roles = (Collection<String>) realmAccess.get("roles");
            return roles.stream()
                    .map(r -> new SimpleGrantedAuthority("ROLE_" + r.toUpperCase()))
                    .toList();

        }
        return new ArrayList<SimpleGrantedAuthority>();
    }
}
