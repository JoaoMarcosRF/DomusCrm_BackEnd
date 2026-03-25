package com.domus.api.service.lead;

import com.domus.api.dto.LeadRequest;
import com.domus.api.model.image.Image;
import com.domus.api.model.lead.Lead;
import com.domus.api.model.property.Property;
import com.domus.api.model.shared.Role;
import com.domus.api.repository.broker.BrokerRepository;
import com.domus.api.repository.image.ImageRepository;
import com.domus.api.repository.lead.LeadRepository;
import com.domus.api.repository.property.PropertyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LeadService {
    @PersistenceContext
    private EntityManager entityManager;

    private final LeadRepository repository;
    private final BrokerRepository brokerRepository;
    private final PropertyRepository propertyRepository;

    public LeadService(LeadRepository repository, BrokerRepository brokerRepository, PropertyRepository propertyRepository){
        this.repository = repository;
        this.brokerRepository = brokerRepository;
        this.propertyRepository = propertyRepository;

    }

    public Lead save(LeadRequest request){

        Lead lead = new Lead();

        lead.setName(request.name());
        lead.setEmail(request.email());
        lead.setPhoneNumber(request.phoneNumber());
        lead.setInterestDate(LocalDate.now());
        lead.setMessage(request.message());
        lead.setLeadStatus(request.leadStatus());

        lead.setBroker(brokerRepository.findById(
                request.brokerId()).orElseThrow(() -> new RuntimeException("Broker not found."))
        );

        lead.setProperty(propertyRepository.findById(
                request.propertyId()).orElseThrow(() -> new RuntimeException("Property not found."))
        );

        lead.setRole(Role.USER);


        return repository.save(lead);
    }

    public Optional<Lead> findById(Long id){
        return repository.findById(id);
    }

    public List<Lead> findAll(){
        return repository.findAll();
    }

    public void deleteById(Long id){

        if(!(repository.existsById(id))){
            throw new RuntimeException("Lead don't exist.");
        }

        repository.deleteById(id);
    }

    public Lead update(Long id, LeadRequest request){
        Lead lead = repository.findById(id).orElseThrow(() -> new RuntimeException("Lead not found."));

        lead.setName(request.name());
        lead.setEmail(request.email());
        lead.setPhoneNumber(request.phoneNumber());
        lead.setMessage(request.message());
        lead.setLeadStatus(request.leadStatus());

        lead.setBroker(brokerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Broker not found.")));

        lead.setProperty(propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found.")));

        return repository.save(lead);
    }


    public boolean isBrokerOwner(Long LeadId, Long BrokerId) {
        Lead lead = repository.findById(LeadId)
                .orElseThrow(() -> new RuntimeException("Lead not found."));

        return lead.getBroker().getId().equals(BrokerId);

    }
}
