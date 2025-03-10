package com.microservices.ws.api.jwt;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.List;

public class CustomJwtConverter implements Converter<Jwt, CustomJwt> {

    @Override
    public CustomJwt convert(Jwt source) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        var customJwt = new CustomJwt(source, grantedAuthorities);
        customJwt.setFirstName(source.getClaimAsString("given_name"));
        customJwt.setLastName(source.getClaimAsString("family_name"));
        return customJwt;
    }
}
