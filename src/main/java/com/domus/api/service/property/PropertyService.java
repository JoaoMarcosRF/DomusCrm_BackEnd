package com.domus.api.service.property;

import com.domus.api.dto.PropertyRequest;
import com.domus.api.model.address.Address;
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


    public PropertyService(PropertyRepository repository, ImageRepository imageRepository, LeadRepository leadRepository, BrokerRepository brokerRepository) {
        this.repository = repository;
        this.imageRepository = imageRepository;
        this.leadRepository = leadRepository;
        this.brokerRepository = brokerRepository;
    }

    public Property save(PropertyRequest request) {


        Property property = new Property();

        property.setRegisterDate(LocalDate.now());

        property.setDescription(request.description());
        property.setType(request.type());
        property.setStatus(request.status());
        property.setPurpose(request.purpose());
        property.setTitle(request.title());
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
            property.setBroker(brokerRepository.findById(request.brokerId())
                    .orElseThrow(() -> new RuntimeException("Broker not found."))
            );
        }

        if(request.address() != null){
            Address address = new Address();
            address.setStreet(request.address().street());
            address.setNumber(request.address().number());
            address.setComplement(request.address().complement());
            address.setNeighborhood(request.address().neighborhood());
            address.setCity(request.address().city());
            address.setState(request.address().state());
            address.setCep(request.address().cep());

            property.setAddress(address);
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

        if(!(repository.existsById(id))){
            throw new RuntimeException("Proeperty don't exist.");
        }

        repository.deleteById(id);
    }


    public Property update(Long id, PropertyRequest request){
        Property property = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found."));

        property.setDescription(request.description());
        property.setType(request.type());
        property.setStatus(request.status());
        property.setPurpose(request.purpose());
        property.setTitle(request.title());
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
            property.setBroker(brokerRepository.findById(request.brokerId())
                    .orElseThrow(() -> new RuntimeException("Broker not found."))
            );
        }

        if(request.address() != null){
            Address address = property.getAddress();

            if(address == null){
                address = new Address();
            }

            address.setStreet(request.address().street());
            address.setNumber(request.address().number());
            address.setComplement(request.address().complement());
            address.setNeighborhood(request.address().neighborhood());
            address.setCity(request.address().city());
            address.setState(request.address().state());
            address.setCep(request.address().cep());

            property.setAddress(address);
        }

        return repository.save(property);
    }


    public boolean isBrokerOwner(Long propertyID, Long brokerId){
        Property property = repository.findById(propertyID)
                .orElseThrow(() -> new RuntimeException("Property not found."));

        return property.getBroker().getId().equals(brokerId);
    }
}
