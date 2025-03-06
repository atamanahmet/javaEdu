package com.grade.grade_submition.security.filter;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import com.grade.grade_submition.exceptions.ContentNotFoundException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ContentNotFoundException contentNotFoundException) {
            response.setStatus(404);
            response.getWriter().write("Username doesn't exist");
        } catch (RuntimeException ex) {
            response.setStatus(400);
            response.getWriter().write("Wrong request body");
            response.getWriter().flush();
        }

    }

}
