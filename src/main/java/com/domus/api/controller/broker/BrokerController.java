package com.domus.api.controller.broker;

import com.domus.api.dto.BrokerRequest;
import com.domus.api.dto.response.ApiResponse;
import com.domus.api.model.broker.Broker;
import com.domus.api.service.broker.BrokerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/broker")
public class BrokerController {

    private final BrokerService service;

    public BrokerController(BrokerService service) {
        this.service = service;
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<Broker>> addBroker(@RequestBody BrokerRequest request) {
        Broker created = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Broker created successfully.", created));
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Broker>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.findById(id)));
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Broker>>> findAll() {
        return ResponseEntity.ok(ApiResponse.ok(service.findAll()));
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Broker>> deleteById(@PathVariable Long id) {
        Broker broker = service.findById(id);
        service.deleteById(id);
        return ResponseEntity.ok(ApiResponse.ok("Broker deleted successfully.", broker));
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Broker>> updateBroker(
            @PathVariable Long id,
            @RequestBody BrokerRequest request) {
        Broker updated = service.update(id, request);
        return ResponseEntity.ok(ApiResponse.ok("Broker updated successfully.", updated));
    }
}
