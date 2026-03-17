package com.domus.api.service.address;

import com.domus.api.dto.AddressRequest;
import com.domus.api.model.address.Address;
import com.domus.api.model.property.Property;
import com.domus.api.repository.address.AddressRepository;
import com.domus.api.repository.property.PropertyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AddressService {
    @PersistenceContext
    private EntityManager entityManager;

    private final AddressRepository repository;
    private final PropertyRepository propertyRepository;

    public AddressService(AddressRepository repository, PropertyRepository propertyRepository){
        this.propertyRepository = propertyRepository;
        this.repository = repository;
    }

    public Address save(AddressRequest request){



        Address address = new Address();
        address.setStreet(request.street());
        address.setNumber(request.number());
        address.setComplement(request.complement());
        address.setNeighborhood(request.complement());
        address.setCity(request.city());
        address.setState(request.state());
        address.setCep(request.cep());

        if(request.propertyId() != null){
            Property properties = propertyRepository.findById(request.propertyId()).orElseThrow();
            address.setProperty(properties);
        }

        return repository.save(address);
    }

    public Optional<Address> findById(Long id){
        return repository.findById(id);
    }

    public List<Address> findAll(){
        return repository.findAll();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

}
