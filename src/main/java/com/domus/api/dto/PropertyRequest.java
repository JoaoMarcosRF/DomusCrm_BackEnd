package com.domus.api.dto;

import com.domus.api.model.property.PropertyPorpuse;
import com.domus.api.model.property.PropertyStatus;
import com.domus.api.model.property.PropertyType;

import java.math.BigDecimal;
import java.util.List;

public record PropertyRequest(
    String description,
    PropertyType type,
    PropertyStatus status,
    PropertyPorpuse purpose,
    String title,
    String bedroomQnt,
    String bathRoomQnt,
    String parkingSpaces,
    BigDecimal value,
    BigDecimal footage,
    List<Long> imageIds,
    List<Long> leadsIds,
    AddressRequest address,
    Long brokerId
) {}

/*

PADRÃO DO JSON

{
  "description": "Apartamento amplo próximo à praia",
  "type": "APARTMENT",
  "status": "AVAILABLE",
  "purpose": "SALE",
  "title": "Apartamento em Boa Viagem",
  "bedroomQnt": "3",
  "bathRoomQnt": "2",
  "parkingSpaces": "1",
  "value": 450000.00,
  "footage": 85.5,
  "imageIds": [],
  "leadsIds": [],
  "brokerId": 1,
  "address": {
    "street": "Rua dos Navegantes",
    "number": "123",
    "complement": "Apto 802",
    "neighborhood": "Boa Viagem",
    "city": "Recife",
    "state": "PE",
    "cep": "51021-010",
    "propertyId": null
  }
}

 */
