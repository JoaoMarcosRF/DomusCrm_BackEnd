package com.domus.api.dto;

import com.domus.api.model.shared.Role;

import java.util.List;

public record BrokerRequest(
    String name,
    String email,
    String phoneNumber,
    String CRECI,
    String password,
    List<Long> proprietiesIds,
    List<Long> leadsIds,
    Role role
) {}

/*

PADRÃO DO JSON

{
  "name": "João Silva",
  "email": "joao@imobiliaria.com",
  "phoneNumber": "81999999999",
  "CRECI": "12445-PE",
  "password": "123456",
  "brokerRole": "ADMIN"
}

 */
