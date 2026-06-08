package com.domus.api.service.image;

import com.domus.api.dto.ImageRequest;
import com.domus.api.exception.ResourceNotFoundException;
import com.domus.api.model.image.Image;
import com.domus.api.model.property.Property;
import com.domus.api.repository.image.ImageRepository;
import com.domus.api.repository.property.PropertyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ImageService {

    private final ImageRepository repository;
    private final PropertyRepository propertyRepository;

    public ImageService(ImageRepository repository, PropertyRepository propertyRepository) {
        this.repository = repository;
        this.propertyRepository = propertyRepository;
    }

    public Image save(ImageRequest request) {
        Image image = new Image();
        image.setUrl(request.url());
        image.setIsPrincipal(request.isPrincipal());
        image.setDisplayOrder(request.displayOrder());

        Property property = propertyRepository.findById(request.propertyId())
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + request.propertyId()));

        property.addImage(image);
        image.setProperty(property);

        return repository.save(image);
    }

    public Image findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found with id: " + id));
    }

    public List<Image> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Image not found with id: " + id);
        }
        repository.deleteById(id);
    }

    public Image update(Long id, ImageRequest request) {
        Image image = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found with id: " + id));

        image.setDisplayOrder(request.displayOrder());
        image.setUrl(request.url());
        image.setIsPrincipal(request.isPrincipal());

        image.setProperty(propertyRepository.findById(request.propertyId())
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + request.propertyId())));

        return repository.save(image);
    }

    public boolean isBrokerOwner(Long imageId, Long brokerId) {
        Image image = repository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found with id: " + imageId));
        return image.getProperty().getBroker().getId().equals(brokerId);
    }
}
