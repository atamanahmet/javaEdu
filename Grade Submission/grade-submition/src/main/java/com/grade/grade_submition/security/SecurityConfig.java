package com.grade.grade_submition.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class SecurityConfig {

    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    @Bean
    public SecurityFilterChain securityFilter(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> {
                    request
                            .requestMatchers(HttpMethod.GET).permitAll()
                            .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST).hasAnyRole("ADMIN")
                            .anyRequest()
                            .authenticated();
                })
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetails() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(bCryptEncoder.encode("adminpass"))
                .roles("ADMIN")
                .build();
        // System.out.println(admin.getPassword());
        UserDetails user = User.builder()
                .username("user")
                .password(bCryptEncoder.encode("adminpass"))
                .roles("USER")
                .build();

        System.out.println("Admin roles: " + admin.getAuthorities()); // Debug print
        System.out.println("User roles: " + user.getAuthorities()); // Debug print

        return new InMemoryUserDetailsManager(admin, user);
    }

}
