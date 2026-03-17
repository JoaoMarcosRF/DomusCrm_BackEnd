package com.domus.api.dto;

import com.domus.api.model.lead.LeadStatus;

public record LeadRequest (
    String message,
    LeadStatus leadStatus,
    Long propertyId,
    Long brokerId,
    Long costumerId
) {}


/*
{
  "message": "Tenho interesse neste imóvel. Poderia enviar mais informações?",
  "leadStatus": "QUALIFIED",
  "propertyId": 1,
  "brokerId": 2,
  "costumerId": 1
}
 */
