package com.domus.api.model.lead;

import com.domus.api.model.broker.Broker;
import com.domus.api.model.costumer.Costumer;
import com.domus.api.model.property.Property;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

@Entity
@Table(name = "leads")
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne(optional = false)
    @JoinColumn(name = "costumer_id",  nullable = false)
    private Costumer costumer;

}
