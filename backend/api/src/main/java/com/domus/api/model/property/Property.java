package com.domus.api.model.property;

import com.domus.api.model.address.Address;
import com.domus.api.model.broker.Broker;
import com.domus.api.model.image.Image;
import com.domus.api.model.lead.Lead;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "owners")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private PropertyType type;
    private String tittle;
    private String status;
    private String bedroomQnt;
    private String bathRoomQnt;
    private String parkingSpaces;

    private BigDecimal value;
    private BigDecimal footage;

    private LocalDate registerDate;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "property",  cascade = CascadeType.ALL)
    private List<Lead> leads = new ArrayList<>();


    @ManyToOne(optional = false)
    @JoinColumn(name = "address_id",  nullable = false)
    private Address address;

    @ManyToOne(optional = true)
    @JoinColumn(name = "broker_id",  nullable = true)
    private Broker broker;



    @Enumerated(EnumType.STRING)
    private PropertyPorpuse propertyPorpuse;

}
