package com.domus.api.model.address;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

}
