package com.domus.api.dto;

public record ImageRequest(
        String url,
        Integer displayOrder,
        Boolean isPrincipal,
        Long propertyId
) {}


/*

PADRÃO JSON

{
  "url": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTLWfsn6CKhmmhaUOLCbKLCndklHShvY72fTg&s",
  "displayOrder": 2,
  "isPrincipal": false,
  "propertyId": 1
}
 */
