package com.domus.api.service.lead;

import com.domus.api.dto.LeadRequest;
import com.domus.api.exception.ResourceNotFoundException;
import com.domus.api.model.lead.Lead;
import com.domus.api.model.shared.Role;
import com.domus.api.repository.broker.BrokerRepository;
import com.domus.api.repository.lead.LeadRepository;
import com.domus.api.repository.property.PropertyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class LeadService {

    private final LeadRepository repository;
    private final BrokerRepository brokerRepository;
    private final PropertyRepository propertyRepository;

    public LeadService(LeadRepository repository,
                       BrokerRepository brokerRepository,
                       PropertyRepository propertyRepository) {
        this.repository = repository;
        this.brokerRepository = brokerRepository;
        this.propertyRepository = propertyRepository;
    }

    public Lead save(LeadRequest request) {
        Lead lead = new Lead();
        lead.setName(request.name());
        lead.setEmail(request.email());
        lead.setPhoneNumber(request.phoneNumber());
        lead.setInterestDate(LocalDate.now());
        lead.setMessage(request.message());
        lead.setLeadStatus(request.leadStatus());
        lead.setRole(Role.USER);

        lead.setBroker(brokerRepository.findById(request.brokerId())
                .orElseThrow(() -> new ResourceNotFoundException("Broker not found with id: " + request.brokerId())));

        lead.setProperty(propertyRepository.findById(request.propertyId())
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + request.propertyId())));

        return repository.save(lead);
    }

    public Lead findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lead not found with id: " + id));
    }

    public List<Lead> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Lead not found with id: " + id);
        }
        repository.deleteById(id);
    }

    public Lead update(Long id, LeadRequest request) {
        Lead lead = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lead not found with id: " + id));

        lead.setName(request.name());
        lead.setEmail(request.email());
        lead.setPhoneNumber(request.phoneNumber());
        lead.setMessage(request.message());
        lead.setLeadStatus(request.leadStatus());

        lead.setBroker(brokerRepository.findById(request.brokerId())
                .orElseThrow(() -> new ResourceNotFoundException("Broker not found with id: " + request.brokerId())));

        lead.setProperty(propertyRepository.findById(request.propertyId())
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + request.propertyId())));

        return repository.save(lead);
    }

    public boolean isBrokerOwner(Long leadId, Long brokerId) {
        Lead lead = repository.findById(leadId)
                .orElseThrow(() -> new ResourceNotFoundException("Lead not found with id: " + leadId));
        return lead.getBroker().getId().equals(brokerId);
    }
}
