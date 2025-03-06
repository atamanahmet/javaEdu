package com.grade.grade_submition.security.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.grade.grade_submition.domain.User;
import com.grade.grade_submition.service.UserService;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User dbUser = userService.findUserByUserName(authentication.getName());
        if (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), dbUser.getPassword())) {
            throw new BadCredentialsException("You provided an incorrect Password");
        }
        return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials());
    }

}
