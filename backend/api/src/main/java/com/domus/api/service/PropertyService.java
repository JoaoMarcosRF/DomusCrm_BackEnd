package com.domus.api.service;

import com.domus.api.model.property.Property;
import com.domus.api.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    private final PropertyRepository  repository;

    public PropertyService(PropertyRepository repository) {
        this.repository = repository;
    }

    public void createProperty(Property property) {
        if(property.getValue().compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalArgumentException("Value cannot be less than zero");
        }

        repository.save(property);
    }

    public List<Property> findAll() {
        return repository.findAll();
    }

    public Optional<Property> findById(Long id) {
        return repository.findById(id);
    }

//ja vem por padrao no JPA:
//    findAll()
//
//    findById()
//
//    save()
//
//    delete()
//
}
