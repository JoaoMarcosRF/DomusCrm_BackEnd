package com.domus.api.model.image;

import com.domus.api.model.property.Property;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private Integer displayOrder;

    private Boolean isPrincipal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "property_id",   nullable = false)
    @JsonIgnore
    private Property property;
}
