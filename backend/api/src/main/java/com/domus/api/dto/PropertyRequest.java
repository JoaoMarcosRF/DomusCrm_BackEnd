package com.domus.api.dto;

import com.domus.api.model.address.Address;
import com.domus.api.model.broker.Broker;
import com.domus.api.model.image.Image;
import com.domus.api.model.lead.Lead;
import com.domus.api.model.property.PropertyPorpuse;
import com.domus.api.model.property.PropertyStatus;
import com.domus.api.model.property.PropertyType;

import java.math.BigDecimal;
import java.util.List;

public record PropertyRequest(
    String description,
    PropertyType type,
    PropertyStatus status,
    PropertyPorpuse porpuse,
    String tittle,
    String bedroomQnt,
    String bathRoomQnt,
    String parkingSpaces,
    BigDecimal value,
    BigDecimal footage,
    List<Long> imageIds,
    List<Long> leadsIds,
    Long addressId,
    Long brokerId
) {}

/*

{
  "description": "Apartamento amplo próximo à praia",
  "type": "APARTMENT",
  "status": "AVAILABLE",
  "porpuse": "SALE",
  "tittle": "Apartamento em Boa Viagem",
  "bedroomQnt": "3",
  "bathRoomQnt": "2",
  "parkingSpaces": "1",
  "value": 450000.00,
  "footage": 85.5,
  "imageIds": [1, 2, 3],
  "leadsIds": [10, 11],
  "addressId": 4,
  "brokerId": 2
}

 */
