package com.domus.api.model.broker;

import com.domus.api.model.lead.Lead;
import com.domus.api.model.property.Property;
import com.domus.api.model.property.PropertyPorpuse;
import com.domus.api.model.shared.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "brokers")

public class Broker extends User {


    private String CRECI;
    private String password;

    @OneToMany(mappedBy = "broker", cascade = CascadeType.ALL)
    private List<Property> properties = new ArrayList<>();

    @OneToMany(mappedBy = "broker", cascade = CascadeType.ALL)
    private List<Lead> leads = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private BrokerRole brokerRole;



}
