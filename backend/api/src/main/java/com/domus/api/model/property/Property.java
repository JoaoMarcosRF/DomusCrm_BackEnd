package com.domus.api.model.property;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter

@Entity
@Table(name = "owners")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String type;
    private String tittle;
    private String status;
    private String bedroomQnt;
    private String bathRoomQnt;
    private String parkingSpaces;

    private BigDecimal value;
    private BigDecimal footage;

    private LocalDate registerDate;
    private PropertyPorpuse propertyPorpuse;

}
