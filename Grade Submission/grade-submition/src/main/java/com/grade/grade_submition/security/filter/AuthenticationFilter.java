package com.grade.grade_submition.security.filter;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grade.grade_submition.domain.User;
import com.grade.grade_submition.security.SecurityConstants;
import com.grade.grade_submition.security.manager.CustomAuthenticationManager;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.ServletRequest;
// import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private CustomAuthenticationManager authenticationManager;

    // @Override
    // public void doFilter(ServletRequest request, ServletResponse response,
    // FilterChain chain)
    // throws IOException, ServletException {
    // chain.doFilter(request, response);
    // }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            // System.out.println(user.getUsername());
            // System.out.println(user.getPassword());

            return authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),
                            user.getPassword()));

        } catch (IOException e) {
            // System.out.println("RuntimeException");
            throw new RuntimeException();
        }

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(401);
        response.getWriter().write(failed.getMessage());
        System.out.println("Authentication Unsuccessfull");

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        String token = JWT.create()
                .withSubject(authResult.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRE_IN_MILLISECONDS))
                .sign(Algorithm.HMAC256(SecurityConstants.SECRET_KEY));

        response.addHeader(SecurityConstants.AUTHORIZATION, SecurityConstants.BEARER + token);
        // response.setStatus(200);
        // response.getWriter().write("Authentication successfull");
        // System.out.println("Authentication successfull");
    }

}
