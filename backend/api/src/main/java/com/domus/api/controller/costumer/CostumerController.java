package com.domus.api.controller.costumer;

import com.domus.api.dto.CostumerRequest;
import com.domus.api.model.costumer.Costumer;
import com.domus.api.model.image.Image;
import com.domus.api.service.costumer.CostumerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/costumer")
public class CostumerController {
    private final CostumerService service;

    public CostumerController(CostumerService service){
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<CostumerRequest> addCostumer(@RequestBody CostumerRequest request){
        service.save(request);
        return ResponseEntity.ok().body(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Costumer>> findById(@PathVariable Long id){
        Optional<Costumer> costumer = service.findById(id);
        return ResponseEntity.ok().body(costumer);
    }

    @GetMapping()
    public List<Costumer> findAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Costumer>> deleteById(@PathVariable Long id){
        Optional<Costumer> costumer = service.findById(id);
        service.deleteById(id);
        return ResponseEntity.ok().body(costumer);
    }
}
