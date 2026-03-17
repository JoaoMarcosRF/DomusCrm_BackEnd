package com.domus.api.service.costumer;

import com.domus.api.dto.CostumerRequest;
import com.domus.api.model.costumer.Costumer;
import com.domus.api.model.image.Image;
import com.domus.api.model.lead.Lead;
import com.domus.api.repository.costumer.CostumerRepository;
import com.domus.api.repository.image.ImageRepository;
import com.domus.api.repository.lead.LeadRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CostumerService {
    @PersistenceContext
    private EntityManager entityManager;

    private final CostumerRepository repository;
    private final LeadRepository leadRepository;

    public CostumerService(CostumerRepository repository, LeadRepository leadRepository) {
        this.repository = repository;
        this.leadRepository = leadRepository;
    }

    public Costumer save(CostumerRequest request) {


        Costumer costumer = new Costumer();

        costumer.setName(request.name());
        costumer.setEmail(request.email());
        costumer.setPhoneNumber(request.phoneNumber());
        costumer.setCpf(request.cpf());

        if(request.leadIds() != null){
            List<Lead> leads = leadRepository.findAllById(request.leadIds());
            costumer.setRegisterData(LocalDate.now());
        }

        return repository.save(costumer);
    }

    public Optional<Costumer> findById(Long id){
        return repository.findById(id);
    }

    public List<Costumer> findAll(){
        return repository.findAll();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
