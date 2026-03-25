package com.domus.api.service.image;

import com.domus.api.dto.ImageRequest;
import com.domus.api.model.image.Image;
import com.domus.api.model.property.Property;
import com.domus.api.repository.image.ImageRepository;
import com.domus.api.repository.property.PropertyRepository;
import com.domus.api.service.property.PropertyService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImageService {
    @PersistenceContext
    private EntityManager entityManager;

    private final ImageRepository repository;
    private final PropertyRepository propertyRepository;

    public ImageService(ImageRepository repository, PropertyRepository propertyRepository){
        this.repository = repository;
        this.propertyRepository = propertyRepository;
    }

    public Image save(ImageRequest request){


        Image image = new Image();
        image.setUrl(request.url());
        image.setIsPrincipal(request.isPrincipal());
        image.setDisplayOrder(request.displayOrder());

        if(request.propertyId() != null){
            Property property = propertyRepository.findById(request.propertyId())
                    .orElseThrow(() ->  new RuntimeException("property not found."));

            property.addImage(image);

            image.setProperty(property);
        }



        return repository.save(image);
    }

    public Optional<Image> findById(Long id){
        return repository.findById(id);
    }

    public List<Image> findAll(){
        return repository.findAll();
    }

    public void deleteById(Long id){

        if(!(repository.existsById(id))){
            throw new RuntimeException("Image don't exist.");
        }
        repository.deleteById(id);
    }

    public Image update(Long id, ImageRequest request){
        Image image = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image don't exist."));

        image.setDisplayOrder(request.displayOrder());
        image.setUrl(request.url());
        image.setIsPrincipal(request.isPrincipal());

        image.setProperty(propertyRepository.findById(request.propertyId())
        .orElseThrow(() -> new RuntimeException("property not found.")));

        return repository.save(image);
    }

    public boolean isBrokerOwner(Long ImageId, Long BrokerId) {
        Image image = repository.findById(ImageId)
                .orElseThrow(() -> new RuntimeException("Image don't exist."));

        return image.getProperty().getBroker().getId().equals(BrokerId);
    }
}
