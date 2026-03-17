package com.domus.api.dto;

import com.domus.api.model.broker.BrokerRole;

import java.util.List;

public record BrokerRequest(
    String name,
    String email,
    String phoneNumber,
    String CRECI,
    String password,
    List<Long> proprietiesIds,
    List<Long> leadsIds,
    BrokerRole brokerRole
) {}
