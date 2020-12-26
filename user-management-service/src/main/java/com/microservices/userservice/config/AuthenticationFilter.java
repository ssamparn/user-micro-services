package com.microservices.userservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.userservice.entity.UserEntity;
import com.microservices.userservice.properties.UserProperties;
import com.microservices.userservice.service.UserService;
import com.microservices.userservice.web.model.request.UserSigninRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserService userService;
    private UserProperties properties;

    public AuthenticationFilter(UserService userService, UserProperties properties) {
        this.userService = userService;
        this.properties = properties;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        try {
            UserSigninRequest credentials = new ObjectMapper()
                    .readValue(request.getInputStream(), UserSigninRequest.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getEmail(),
                            credentials.getPassword(),
                            new ArrayList<>())
                    );
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) {

        String userName = ((User) auth.getPrincipal()).getUsername();

        UserEntity userEntity = userService.getUserDetailsByEmail(userName);

        String token = Jwts.builder()
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(properties.getExpirationTime())))
                .signWith(SignatureAlgorithm.HS512, properties.getSecret())
                .compact();

        response.addHeader("token", token);
        response.addHeader("UserId", userEntity.getUserId());
    }
}
