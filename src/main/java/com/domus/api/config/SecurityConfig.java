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

                        .requestMatchers("/lead/**").permitAll()
                        .requestMatchers("/auths/**").permitAll()
                        .requestMatchers("/property/**").permitAll()

                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/broker/**").hasAnyRole("ADMIN", "BROKER")
                        .requestMatchers("/image/**").hasAnyRole("ADMIN", "BROKER")

                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtConfig, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
