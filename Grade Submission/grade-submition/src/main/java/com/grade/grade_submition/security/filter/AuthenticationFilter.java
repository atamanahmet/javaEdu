package com.grade.grade_submition.security.filter;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grade.grade_submition.domain.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    // @Override
    // public void doFilter(ServletRequest request, ServletResponse response,
    // FilterChain chain)
    // throws IOException, ServletException {
    // super.doFilter(request, response, chain);
    // }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());

        } catch (IOException e) {
            // throw new RuntimeException();
            System.out.println("RuntimeException");
        }

        return super.attemptAuthentication(request, response);
    }

}
