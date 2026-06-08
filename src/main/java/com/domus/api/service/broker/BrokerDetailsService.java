package com.domus.api.service.broker;

import com.domus.api.repository.broker.BrokerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BrokerDetailsService implements UserDetailsService {

    private final BrokerRepository brokerRepository;

    public BrokerDetailsService(BrokerRepository brokerRepository) {
        this.brokerRepository = brokerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return brokerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Broker not found with email: " + email));
    }
}
