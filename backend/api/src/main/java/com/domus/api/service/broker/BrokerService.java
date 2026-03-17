package com.domus.api.service.broker;

import com.domus.api.dto.BrokerRequest;
import com.domus.api.model.broker.Broker;
import com.domus.api.model.lead.Lead;
import com.domus.api.model.property.Property;
import com.domus.api.repository.broker.BrokerRepository;
import com.domus.api.repository.lead.LeadRepository;
import com.domus.api.repository.property.PropertyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BrokerService {
    @PersistenceContext
    private EntityManager entityManager;


    private final BrokerRepository repository;
    private final PropertyRepository propertyRepository;
    private final LeadRepository leadRepository;

    public BrokerService(BrokerRepository repository, PropertyRepository propertyRepository, LeadRepository leadRepository) {
        this.repository = repository;
        this.propertyRepository = propertyRepository;
        this.leadRepository = leadRepository;
    }

    public Broker save(BrokerRequest request) {

        Broker broker = new Broker();
        broker.setName(request.name());
        broker.setEmail(request.email());
        broker.setPhoneNumber(request.phoneNumber());
        broker.setPassword(request.password());
        broker.setCRECI(request.CRECI());

        if(request.proprietiesIds() != null){
            List<Property> properties = propertyRepository.findAllById(request.proprietiesIds());
            broker.setProperties(properties);
        }
        if(request.leadsIds() != null){
            List<Lead> leads = leadRepository.findAllById(request.leadsIds());
            broker.setLeads(leads);
        }

        return repository.save(broker);
    }

    public Optional<Broker> findById(Long id) {
        return repository.findById(id);
    }

    public List<Broker> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }


}
