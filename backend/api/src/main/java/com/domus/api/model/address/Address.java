package com.domus.api.model.address;

import com.domus.api.model.property.Property;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;

    //criar uma classe cep
    private String cep;

    @OneToOne(mappedBy = "address", optional = true)
    @JsonIgnore
    private Property property;
}
