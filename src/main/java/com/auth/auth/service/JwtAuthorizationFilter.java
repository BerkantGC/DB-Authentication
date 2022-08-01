package com.auth.auth.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private static final String TOKEN_PREFIX = "Bearer ";
    private final CustomUserDetailsService customUserDetailsService;
    private AuthenticationManager authenticationManager;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,CustomUserDetailsService customUserDetailsService){
        super(authenticationManager);
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
       UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) getUsernamePasswordAuthentication(request);
       if(auth == null){
           chain.doFilter(request, response);
           return;
       }
        SecurityContextHolder.getContext().setAuthentication(auth);
       chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request){
        String token = request.getHeader(JwtProperties.HEADER_STRING);
        System.out.println(token);
        if(token!=null){
            // parse token and validate it
            String userName = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build()
                    .verify(token.replace(JwtProperties.TOKEN_PREFIX, ""))
                    .getSubject();

            if (userName!= null){
                CustomUserDetail userDetail = (CustomUserDetail) customUserDetailsService.loadUserByUsername(userName);
                return new UsernamePasswordAuthenticationToken(userDetail.getUsername(), null, userDetail.getAuthorities());
            }
            return null;
        }
        else System.out.println("User not found");
        return null;
    }
}
