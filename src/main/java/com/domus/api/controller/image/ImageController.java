package com.domus.api.controller.image;

import com.domus.api.dto.ImageRequest;
import com.domus.api.dto.LeadRequest;
import com.domus.api.model.image.Image;
import com.domus.api.service.image.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService service;

    public ImageController(ImageService service){
        this.service = service;
    }

    @PreAuthorize("hasAnyHole('ADMIN', 'BROKER')")
    @PostMapping()
    public ResponseEntity<ImageRequest> addImage(@RequestBody ImageRequest dto){
        service.save(dto);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Image>> findById(@PathVariable Long id){
        Optional<Image> image = service.findById(id);
        return ResponseEntity.ok().body(image);
    }

    @GetMapping()
    public List<Image> findAll() {
        return service.findAll();
    }

    @PreAuthorize("@ImageService.isBrokerOwner(#id, authentication.principal.id) or hasHole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Image>> deleteById(@PathVariable Long id){
        Optional<Image> image = service.findById(id);
        service.deleteById(id);
        return ResponseEntity.ok().body(image);
    }

    @PreAuthorize("@ImageService.isBrokerOwner(#id, authentication.principal.id) or hasHole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ImageRequest> updateImage(
            @PathVariable Long id,
            @RequestBody ImageRequest image
    ) {
        service.update(id, image);
        return ResponseEntity.ok().body(image);
    }
}
