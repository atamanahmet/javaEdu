package com.grade.grade_submition.security.filter;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.grade.grade_submition.exceptions.ContentNotFoundException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

    // @Override
    // protected boolean shouldNotFilter(HttpServletRequest request) throws
    // ServletException {
    // String registerPath = request.getServletPath();
    // return registerPath.equals("/user/register") ||
    // registerPath.equals("/signup");
    // }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ContentNotFoundException contentNotFoundException) {
            response.setStatus(404);
            response.getWriter().write("Content doesn't exist");
        } catch (JWTVerificationException ex) {
            response.setStatus(403);
            response.getWriter().write("Unauthorized access.");
            response.getWriter().flush();
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
            response.setStatus(400);
            response.getWriter().write("RuntimeEx.Wrong request body" + ex.getMessage());
            response.getWriter().flush();
        }

    }

}
