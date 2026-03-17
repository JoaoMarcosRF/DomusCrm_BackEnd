package com.domus.api.service.property;

import com.domus.api.dto.PropertyRequest;
import com.domus.api.model.address.Address;
import com.domus.api.model.broker.Broker;
import com.domus.api.model.image.Image;
import com.domus.api.model.lead.Lead;
import com.domus.api.model.property.Property;
import com.domus.api.repository.address.AddressRepository;
import com.domus.api.repository.broker.BrokerRepository;
import com.domus.api.repository.image.ImageRepository;
import com.domus.api.repository.lead.LeadRepository;
import com.domus.api.repository.property.PropertyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PropertyService {
    @PersistenceContext
    private EntityManager entityManager;


    private final PropertyRepository  repository;
    private final ImageRepository imageRepository;
    private final LeadRepository leadRepository;
    private final BrokerRepository brokerRepository;
    private final AddressRepository addressRepository;


    public PropertyService(PropertyRepository repository, ImageRepository imageRepository, LeadRepository leadRepository, BrokerRepository brokerRepository, AddressRepository addressRepository) {
        this.repository = repository;
        this.imageRepository = imageRepository;
        this.leadRepository = leadRepository;
        this.brokerRepository = brokerRepository;
        this.addressRepository = addressRepository;
    }

    public Property save(PropertyRequest request) {


        Property property = new Property();
        property.setRegisterDate(LocalDate.now());
        property.setDescription(request.description());
        property.setType(request.type());
        property.setStatus(request.status());
        property.setPorpuse(request.porpuse());
        property.setTittle(request.tittle());
        property.setBathRoomQnt(request.bathRoomQnt());
        property.setBedroomQnt(request.bedroomQnt());
        property.setParkingSpaces(request.parkingSpaces());
        property.setValue(request.value());
        property.setFootage(request.footage());


        if(request.leadsIds() != null){
            List<Lead> leads = leadRepository.findAllById(request.leadsIds());
            property.setLeads(leads);
        }

        if(request.imageIds() != null){
            List<Image> images = imageRepository.findAllById(request.imageIds());
            property.setImages(images);
        }

        if(request.brokerId() != null){
            property.setBroker(brokerRepository.findById(
                    request.brokerId()).orElseThrow(() -> new RuntimeException("Broker not found."))
            );
        }

        if(request.addressId() != null){
            property.setAddress(
                    addressRepository.findById(request.addressId()).orElseThrow(() -> new RuntimeException("Address not found."))
            );
        }

        return repository.save(property);
    }

    public List<Property> findAll() {
        return repository.findAll();
    }

    public Optional<Property> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
