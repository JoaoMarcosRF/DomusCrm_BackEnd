package com.domus.api.dto;

import java.util.List;

public record CostumerRequest(
        String name,
        String email,
        String phoneNumber,
        String cpf,
        List<Long> leadIds
) {}

/*

PADRÃO DO JSON
{
  "name": "Maria Souza",
  "email": "maria@email.com",
  "phoneNumber": "81988888888",
  "cpf": "12345678900"
}
 */