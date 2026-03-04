package com.domus.api.model.image;

import com.domus.api.model.property.Property;
import jakarta.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private int order;

    private Boolean isPrincipal;

    @ManyToOne
    private Property property;

}
