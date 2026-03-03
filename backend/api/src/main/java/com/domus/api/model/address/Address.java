package com.domus.api.model.address;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
    private String street;
    private String number;
    private String complement;

}
