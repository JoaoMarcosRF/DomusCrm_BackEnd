package com.domus.api.config;

import com.domus.api.model.shared.User;
import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtConfig jwtConfig;

    public SecurityConfig(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/broker/**").hasAnyRole("ADMIN", "BROKER")
                        .requestMatchers("/lead/**").permitAll()
                        .requestMatchers("/auths/**").permitAll()
                        .anyRequest().authenticated()
                        .requestMatchers("/image/**").hasAnyRole("ADMIN", "BROKER")
                        .anyRequest().permitAll()
                )
                .addFilterBefore(jwtConfig, UsernamePasswordAuthenticationFilter.class)
                .build();

        return http.build();
    }
}
