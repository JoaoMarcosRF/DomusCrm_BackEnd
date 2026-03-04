package com.domus.api.model.broker;

import com.domus.api.model.property.Property;
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
@AllArgsConstructor
@SuperBuilder


@Entity
@Table(name = "brokers")
@NoArgsConstructor
public class Broker extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String CRECI;

    @OneToMany(mappedBy = "broker", cascade = CascadeType.ALL)
    private List<Property> property = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private BrokerRole brokerRole;

}
