package com.domus.api.dto;

import com.domus.api.model.lead.LeadStatus;

public record LeadRequest (
    String name,
    String email,
    String phoneNumber,
    String message,
    LeadStatus leadStatus,
    Long propertyId,
    Long brokerId
) {}


/*

PADRÃO JSON

{
  "name": "Maria Souza",
  "email": "maria@email.com",
  "phoneNumber": "81988888888",
  "message": "Tenho interesse neste imóvel. Poderia enviar mais informações?",
  "leadStatus": "QUALIFIED",
  "propertyId": 1,
  "brokerId": 1
}
 */
