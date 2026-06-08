package com.domus.api.service.auth;

import com.domus.api.dto.LoginRequest;
import com.domus.api.exception.BusinessException;
import com.domus.api.exception.ResourceNotFoundException;
import com.domus.api.model.broker.Broker;
import com.domus.api.repository.broker.BrokerRepository;
import com.domus.api.service.jwt.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final BrokerRepository brokerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(BrokerRepository brokerRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.brokerRepository = brokerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String login(LoginRequest request) {
        Broker broker = brokerRepository.findByEmail(request.email())
                .orElseThrow(() -> new ResourceNotFoundException("Broker not found with email: " + request.email()));

        if (!passwordEncoder.matches(request.password(), broker.getPassword())) {
            throw new BusinessException("Invalid password.");
        }

        return jwtService.generateToken(broker);
    }
}
