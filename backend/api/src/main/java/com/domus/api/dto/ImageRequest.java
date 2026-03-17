package com.domus.api.dto;

public record ImageRequest(
        String url,
        Integer displayOrder,
        Boolean isPrincipal,
        Long propertyId
) {}


/*
{
  "url": "https://www.infoescola.com/wp-content/uploads/2008/07/tatu_571012585.jpg",
  "displayOrder": 1,
  "isPrincipal": true,
  "propertyId": 5
}
 */
