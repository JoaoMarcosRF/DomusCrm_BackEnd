package com.domus.api.controller.image;

import com.domus.api.dto.ImageRequest;
import com.domus.api.dto.response.ApiResponse;
import com.domus.api.model.image.Image;
import com.domus.api.service.image.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService service;

    public ImageController(ImageService service) {
        this.service = service;
    }

    //@PreAuthorize("hasAnyRole('ADMIN', 'BROKER')")
    @PostMapping
    public ResponseEntity<ApiResponse<Image>> addImage(@RequestBody ImageRequest request) {
        Image created = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Image added successfully.", created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Image>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.findById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Image>>> findAll() {
        return ResponseEntity.ok(ApiResponse.ok(service.findAll()));
    }

    @PreAuthorize("@imageService.isBrokerOwner(#id, authentication.principal.id) or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Image>> deleteById(@PathVariable Long id) {
        Image image = service.findById(id);
        service.deleteById(id);
        return ResponseEntity.ok(ApiResponse.ok("Image deleted successfully.", image));
    }

    @PreAuthorize("@imageService.isBrokerOwner(#id, authentication.principal.id) or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Image>> updateImage(
            @PathVariable Long id,
            @RequestBody ImageRequest request) {
        Image updated = service.update(id, request);
        return ResponseEntity.ok(ApiResponse.ok("Image updated successfully.", updated));
    }
}
