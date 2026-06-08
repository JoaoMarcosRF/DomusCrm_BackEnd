package com.domus.api.config;

import com.domus.api.service.broker.BrokerDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtConfig jwtConfig;
    private final BrokerDetailsService brokerDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(JwtConfig jwtConfig,
                          BrokerDetailsService brokerDetailsService,
                          PasswordEncoder passwordEncoder) {
        this.jwtConfig = jwtConfig;
        this.brokerDetailsService = brokerDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth
                        /*
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/property/**").permitAll()  // catálogo público
                        .requestMatchers("/lead/**").permitAll()       // visitante pode criar lead
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated()
                         */
                        .anyRequest().permitAll()
                )

                // Registra o provedor de autenticação (liga UserDetailsService + PasswordEncoder)
                .authenticationProvider(authenticationProvider())

                // Nosso filtro JWT roda antes do filtro padrão de usuário/senha do Spring
                .addFilterBefore(jwtConfig, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // DaoAuthenticationProvider é o provedor padrão do Spring para autenticação
    // com banco de dados. Ele usa o BrokerDetailsService para carregar o usuário
    // e o PasswordEncoder para comparar a senha.
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(brokerDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
}