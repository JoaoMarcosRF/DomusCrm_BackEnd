package com.domus.api.controller.broker;

import com.domus.api.dto.BrokerRequest;
import com.domus.api.dto.LeadRequest;
import com.domus.api.model.broker.Broker;
import com.domus.api.model.property.Property;
import com.domus.api.service.broker.BrokerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/broker")
public class BrokerController {
    private final BrokerService service;

    public BrokerController(BrokerService service) {
        this.service = service;
    }

    @PreAuthorize("hasHole('ADMIN')")
    @PostMapping()
    public ResponseEntity<BrokerRequest> addBroker(@RequestBody BrokerRequest request) {
        service.save(request);
        return ResponseEntity.ok().body(request);
    }

    @PreAuthorize("hasHole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<?>> findById(@PathVariable Long id){
        Optional<Broker> broker = service.findById(id);
        return ResponseEntity.ok().body(broker);
    }

    @PreAuthorize("hasHole('ADMIN')")
    @GetMapping()
    public List<Broker> findAll(){
        return service.findAll();
    }

    @PreAuthorize("hasHole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Broker>> deleteById(@PathVariable Long id){
        Optional<Broker> broker = service.findById(id);
        service.deleteById(id);
        return ResponseEntity.ok().body(broker);
    }

    @PreAuthorize("hasHole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<BrokerRequest> updateBroker(
            @PathVariable Long id,
            @RequestBody BrokerRequest broker
    ) {
        service.update(id, broker);
        return ResponseEntity.ok().body(broker);
    }

}
