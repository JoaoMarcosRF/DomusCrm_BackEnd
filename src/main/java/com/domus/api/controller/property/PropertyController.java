package com.domus.api.controller.property;

import com.domus.api.dto.PropertyRequest;
import com.domus.api.dto.response.ApiResponse;
import com.domus.api.model.property.Property;
import com.domus.api.service.property.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController {

    private final PropertyService service;

    public PropertyController(PropertyService service) {
        this.service = service;
    }

    //@PreAuthorize("hasAnyRole('ADMIN', 'BROKER')")
    @PostMapping
    public ResponseEntity<ApiResponse<Property>> addProperty(@RequestBody PropertyRequest request) {
        Property created = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Property created successfully.", created));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Property>>> findAll() {
        return ResponseEntity.ok(ApiResponse.ok(service.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Property>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.findById(id)));
    }

    //@PreAuthorize("@propertyService.isBrokerOwner(#id, authentication.principal.id) or hasAnyRole('ADMIN', 'BROKER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Property>> deleteById(@PathVariable Long id) {
        Property property = service.findById(id);
        service.deleteById(id);
        return ResponseEntity.ok(ApiResponse.ok("Property deleted successfully.", property));
    }

    @PreAuthorize("@propertyService.isBrokerOwner(#id, authentication.principal.id) or hasAnyRole('ADMIN', 'BROKER')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Property>> updateProperty(
            @PathVariable Long id,
            @RequestBody PropertyRequest request) {
        Property updated = service.update(id, request);
        return ResponseEntity.ok(ApiResponse.ok("Property updated successfully.", updated));
    }
}
