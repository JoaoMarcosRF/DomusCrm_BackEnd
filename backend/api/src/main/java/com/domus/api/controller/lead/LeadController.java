package com.domus.api.controller.lead;


import com.domus.api.dto.LeadRequest;
import com.domus.api.model.image.Image;
import com.domus.api.model.lead.Lead;
import com.domus.api.service.lead.LeadService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lead")
public class LeadController {
    private final LeadService service;

    public LeadController(LeadService service){this.service = service;}

    @PostMapping()
    public ResponseEntity<LeadRequest> addLead(@RequestBody LeadRequest request){
        service.save(request);
        return ResponseEntity.ok().body(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Lead>> findById(@PathVariable Long id){
        Optional<Lead> lead = service.findById(id);
        return ResponseEntity.ok().body(lead);
    }

    @GetMapping()
    public List<Lead> findAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Lead>> deleteById(@PathVariable Long id){
        Optional<Lead> lead = service.findById(id);
        service.deleteById(id);
        return ResponseEntity.ok().body(lead);
    }

}
