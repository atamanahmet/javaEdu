package com.grade.grade_submition.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.grade.grade_submition.security.filter.AuthenticationFilter;
import com.grade.grade_submition.security.filter.ExceptionHandlerFilter;
import com.grade.grade_submition.security.manager.CustomAuthenticationManager;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class SecurityConfig {

    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    @Autowired
    private CustomAuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain securityFilter(HttpSecurity httpSecurity) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager);

        authenticationFilter.setFilterProcessesUrl("/authenticate");

        httpSecurity
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> {
                    request
                            .requestMatchers(HttpMethod.GET, "/signup").permitAll()
                            .requestMatchers(HttpMethod.POST, "/user/register").permitAll()
                            .requestMatchers(HttpMethod.POST, "/**").hasAnyRole("ADMIN", "USER")
                            .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                            .anyRequest()
                            .authenticated();
                })
                .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
                .addFilter(authenticationFilter)
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
