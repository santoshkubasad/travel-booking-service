package com.travel.booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/swagger-ui/index.html", "/swagger-ui.html", "/api-docs/**").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/webhooks/**").permitAll() // Allow webhook endpoints without auth
                .anyRequest().authenticated()
            )
            .httpBasic(httpBasic -> {}) // Enable HTTP Basic authentication
            .headers(headers -> headers.frameOptions().sameOrigin()); // Allow H2 console frames

        return http.build();
    }
}
