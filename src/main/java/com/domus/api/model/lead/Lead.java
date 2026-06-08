package com.domus.api.model.lead;

import com.domus.api.model.broker.Broker;
import com.domus.api.model.property.Property;
import com.domus.api.model.shared.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "leads")
public class Lead extends User {
    private LocalDate interestDate;
    private String message;

    @Enumerated(EnumType.STRING)
    private LeadStatus leadStatus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "property_id",  nullable = false)
    private Property property;

    @ManyToOne(optional = false)
    @JoinColumn(name = "broker_id", nullable = false)
    @JsonIgnore
    private Broker broker;

}
