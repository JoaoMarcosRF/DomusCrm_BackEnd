package com.domus.api.dto;

import com.domus.api.model.property.Property;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public record AddressRequest(
    String street,
    String number,
    String complement,
    String neighborhood,
    String city,
    String state,
    String cep,
    Long propertyId
) {}


/*
{
        "street": "Rua A",
        "city": "Recife",
        "state": "PE",
        "cep": "50000-000",
        "propertyIds": [1, 2, 3]
}
*/

