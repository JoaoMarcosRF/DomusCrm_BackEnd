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
{
  "name": "João Silva",
  "email": "joao.silva@example.com",
  "phoneNumber": "+55 81 99999-8888",
  "cpf": "123.456.789-10",
  "leadIds": [1, 3, 5]
}
 */