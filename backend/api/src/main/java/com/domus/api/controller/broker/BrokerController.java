package com.domus.api.controller.broker;

import com.domus.api.dto.BrokerRequest;
import com.domus.api.model.broker.Broker;
import com.domus.api.model.property.Property;
import com.domus.api.service.broker.BrokerService;
import org.springframework.http.ResponseEntity;
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

    @PostMapping()
    public ResponseEntity<BrokerRequest> addBroker(@RequestBody BrokerRequest request) {
        service.save(request);
        return ResponseEntity.ok().body(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<?>> findById(@PathVariable Long id){
        Optional<Broker> broker = service.findById(id);
        return ResponseEntity.ok().body(broker);
    }

    @GetMapping()
    public List<Broker> findAll(){
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Broker>> deleteById(@PathVariable Long id){
        Optional<Broker> broker = service.findById(id);
        service.deleteById(id);
        return ResponseEntity.ok().body(broker);
    }

}
