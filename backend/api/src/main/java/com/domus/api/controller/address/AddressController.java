package com.domus.api.controller.address;

import com.domus.api.dto.AddressRequest;
import com.domus.api.model.address.Address;
import com.domus.api.model.property.Property;
import com.domus.api.service.address.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService service;

    public AddressController(AddressService service){
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<AddressRequest> addAddress(@RequestBody AddressRequest request){
        service.save(request);
        return ResponseEntity.ok().body(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<?>> findById(@PathVariable long id){
        Optional<Address> address =  service.findById(id);
        return ResponseEntity.ok().body(address);
    }

    @GetMapping()
    public ResponseEntity<List<Address>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Address>> deleteById(@PathVariable Long id){
        Optional<Address>address = service.findById(id);
        service.deleteById(id);
        return ResponseEntity.ok().body(address);
    }




}
