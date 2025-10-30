package com.MealMonitor.reviewservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Keep CSRF disabled for stateless APIs
                // The .cors() configuration has been removed
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // Keep allowing all requests

        return http.build();
    }

    // The CorsConfigurationSource bean has been completely deleted.
}