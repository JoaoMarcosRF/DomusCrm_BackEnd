package com.domus.api.model.broker;

import com.domus.api.model.lead.Lead;
import com.domus.api.model.property.Property;
import com.domus.api.model.shared.Role;
import com.domus.api.model.shared.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "brokers")

public class Broker extends User {


    private String CRECI;
    private String password;

    @OneToMany(mappedBy = "broker")
    @JsonIgnore
    private List<Property> properties = new ArrayList<>();


    @OneToMany(mappedBy = "broker", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Lead> leads = new ArrayList<>();

}
