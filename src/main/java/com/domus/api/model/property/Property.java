package com.domus.api.model.property;

import com.domus.api.model.address.Address;
import com.domus.api.model.broker.Broker;
import com.domus.api.model.image.Image;
import com.domus.api.model.lead.Lead;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private PropertyType type;

    @Enumerated(EnumType.STRING)
    private PropertyStatus status;

    @Enumerated(EnumType.STRING)
    private PropertyPorpuse purpose;

    private String title;
    private String bedroomQnt;
    private String bathRoomQnt;
    private String parkingSpaces;

    private BigDecimal value;
    private BigDecimal footage;

    private LocalDate registerDate;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "property",  cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Lead> leads = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(optional = true)
    @JoinColumn(name = "broker_id",  nullable = true)
    private Broker broker;


    public void addImage(Image image) {
        if (image == null) {
            this.images = new ArrayList<>();
        }
        this.images.add(image);
    }

    public void removeImage(Image image) {}


}

