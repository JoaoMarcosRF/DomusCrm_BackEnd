package com.domus.api.service.broker;

import com.domus.api.dto.BrokerRequest;
import com.domus.api.exception.BusinessException;
import com.domus.api.exception.ResourceNotFoundException;
import com.domus.api.model.broker.Broker;
import com.domus.api.model.lead.Lead;
import com.domus.api.model.property.Property;
import com.domus.api.model.shared.Role;
import com.domus.api.repository.broker.BrokerRepository;
import com.domus.api.repository.lead.LeadRepository;
import com.domus.api.repository.property.PropertyRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BrokerService {

    private final BrokerRepository repository;
    private final PropertyRepository propertyRepository;
    private final LeadRepository leadRepository;
    private final PasswordEncoder passwordEncoder;

    public BrokerService(BrokerRepository repository,
                         PropertyRepository propertyRepository,
                         LeadRepository leadRepository,
                         PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.propertyRepository = propertyRepository;
        this.leadRepository = leadRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Broker save(BrokerRequest request) {
        Broker broker = new Broker();
        broker.setName(request.name());
        broker.setEmail(request.email());
        broker.setPhoneNumber(request.phoneNumber());
        broker.setPassword(passwordEncoder.encode(request.password()));
        broker.setCRECI(request.CRECI());
        broker.setRole(request.role() != null ? request.role() : Role.BROKER);

        if (request.proprietiesIds() != null) {
            List<Property> properties = propertyRepository.findAllById(request.proprietiesIds());
            broker.setProperties(properties);
        }
        if (request.leadsIds() != null) {
            List<Lead> leads = leadRepository.findAllById(request.leadsIds());
            broker.setLeads(leads);
        }

        return repository.save(broker);
    }

    public Broker findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Broker not found with id: " + id));
    }

    public List<Broker> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        Broker broker = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Broker not found with id: " + id));

        if (!broker.getProperties().isEmpty()) {
            throw new BusinessException("Cannot delete broker; he owns properties linked to his profile.");
        }

        repository.deleteById(id);
    }

    public Broker update(Long id, BrokerRequest request) {
        Broker broker = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Broker not found with id: " + id));

        broker.setName(request.name());
        broker.setEmail(request.email());
        broker.setPhoneNumber(request.phoneNumber());
        broker.setPassword(passwordEncoder.encode(request.password()));
        broker.setCRECI(request.CRECI());
        broker.setRole(request.role());

        if (request.proprietiesIds() != null) {
            List<Property> properties = propertyRepository.findAllById(request.proprietiesIds());
            broker.setProperties(properties);
        }
        if (request.leadsIds() != null) {
            List<Lead> leads = leadRepository.findAllById(request.leadsIds());
            broker.setLeads(leads);
        }

        return repository.save(broker);
    }
}
