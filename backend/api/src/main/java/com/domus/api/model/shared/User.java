package com.domus.api.model.shared;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract class User {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;

}
