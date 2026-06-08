package com.domus.api.controller.lead;

import com.domus.api.dto.LeadRequest;
import com.domus.api.dto.response.ApiResponse;
import com.domus.api.model.lead.Lead;
import com.domus.api.service.lead.LeadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lead")
public class LeadController {

    private final LeadService service;

    public LeadController(LeadService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Lead>> addLead(@RequestBody LeadRequest request) {
        Lead created = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Lead created successfully.", created));
    }

    //@PreAuthorize("@leadService.isBrokerOwner(#id, authentication.principal.id)")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Lead>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.findById(id)));
    }

    //@PreAuthorize("@leadService.isBrokerOwner(#id, authentication.principal.id) or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Lead>>> findAll() {
        return ResponseEntity.ok(ApiResponse.ok(service.findAll()));
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Lead>> deleteById(@PathVariable Long id) {
        Lead lead = service.findById(id);
        service.deleteById(id);
        return ResponseEntity.ok(ApiResponse.ok("Lead deleted successfully.", lead));
    }

    //@PreAuthorize("@leadService.isBrokerOwner(#id, authentication.principal.id) or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Lead>> updateLead(
            @PathVariable Long id,
            @RequestBody LeadRequest request) {
        Lead updated = service.update(id, request);
        return ResponseEntity.ok(ApiResponse.ok("Lead updated successfully.", updated));
    }
}
