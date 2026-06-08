package com.domus.api.repository.broker;

import com.domus.api.model.broker.Broker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrokerRepository extends JpaRepository<Broker, Long> {
    public Optional<Broker> findByEmail(String email);
}
