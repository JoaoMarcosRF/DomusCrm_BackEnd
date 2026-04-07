package com.domus.api.service.auth;

import com.domus.api.dto.LoginRequest;
import com.domus.api.model.broker.Broker;
import com.domus.api.repository.broker.BrokerRepository;
import com.domus.api.service.jwt.JwtService;
import com.domus.api.service.password.PasswordService;

public class AuthService {
    public final BrokerRepository brokerRepository;
    public final PasswordService passwordService;
    public final JwtService jwtService;

    public AuthService(BrokerRepository brokerRepository, PasswordService passwordService, JwtService jwtService) {
        this.brokerRepository = brokerRepository;
        this.passwordService = passwordService;
        this.jwtService = jwtService;
    }

    public String login(LoginRequest request){
        Broker broker = brokerRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Broker not found."));

        if(!(passwordService.validate(broker.getPassword(), request.password()))){
            throw new RuntimeException("Invalid password.");
        }

        return jwtService.generateToken(broker);

    }

}
