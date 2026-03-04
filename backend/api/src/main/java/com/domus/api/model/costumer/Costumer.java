package com.domus.api.model.costumer;


import com.domus.api.model.shared.User;
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

@Entity
@Table(name="costumers")
@NoArgsConstructor
public class Costumer extends User {

    private String cpf;
    private LocalDate registerData;

}
