package com.domus.api.config;

import com.domus.api.model.broker.Broker;
import com.domus.api.repository.broker.BrokerRepository;
import com.domus.api.service.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtConfig extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final BrokerRepository brokerRepository;

    public JwtConfig(JwtService jwtService, BrokerRepository brokerRepository) {
        this.jwtService = jwtService;
        this.brokerRepository = brokerRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {
            String email = jwtService.extractEmail(token);

            Broker broker = brokerRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Broker not found"));

        } catch (Exception e) {
        throw new RuntimeException("Invalid token");
        }

        filterChain.doFilter(request, response);
    }
}