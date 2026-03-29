package com.domus.api.service.password;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private final PasswordEncoder encoder;

    public PasswordService(PasswordEncoder encoder){
        this.encoder = encoder;
    }

    public boolean validate(String password, String hashedPassword){
        if(!(encoder.matches(password, hashedPassword))){
            throw new RuntimeException("Invalid password!");
        }

        return encoder.matches(hashedPassword, password);
    }
}
