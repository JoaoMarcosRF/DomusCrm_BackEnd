package com.domus.api.service.lead;

import com.domus.api.dto.LeadRequest;
import com.domus.api.model.image.Image;
import com.domus.api.model.lead.Lead;
import com.domus.api.model.property.Property;
import com.domus.api.repository.broker.BrokerRepository;
import com.domus.api.repository.costumer.CostumerRepository;
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
    private final CostumerRepository costumerRepository;

    public LeadService(LeadRepository repository, BrokerRepository brokerRepository, PropertyRepository propertyRepository, CostumerRepository costumerRepository){this.repository = repository;
        this.brokerRepository = brokerRepository;
        this.propertyRepository = propertyRepository;
        this.costumerRepository = costumerRepository;
    }

    public Lead save(LeadRequest request){

        Lead lead = new Lead();
        lead.setInterestDate(LocalDate.now());
        lead.setMessage(request.message());
        lead.setLeadStatus(request.leadStatus());

        lead.setBroker(brokerRepository.findById(
                request.brokerId()).orElseThrow(() -> new RuntimeException("Broker not found."))
        );

        lead.setProperty(propertyRepository.findById(
                request.propertyId()).orElseThrow(() -> new RuntimeException("Property not found."))
        );

        lead.setCostumer(costumerRepository.findById(
                request.costumerId()).orElseThrow(() -> new RuntimeException("Costumer not found."))
        );

        return repository.save(lead);
    }

    public Optional<Lead> findById(Long id){
        return repository.findById(id);
    }

    public List<Lead> findAll(){
        return repository.findAll();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
