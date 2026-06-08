package com.domus.api.model.broker;

import com.domus.api.model.lead.Lead;
import com.domus.api.model.property.Property;
import com.domus.api.model.shared.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "brokers")
public class Broker extends User implements UserDetails {

    private String CRECI;
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "broker")
    @JsonIgnore
    private List<Property> properties = new ArrayList<>();

    @OneToMany(mappedBy = "broker", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Lead> leads = new ArrayList<>();

    @Override
    @JsonIgnore
    public String getUsername() {
        return getEmail();
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + getRole().name()));
    }

    //futuramente pra nao esquecer da existencia


    @Override
    @JsonIgnore
    public boolean isAccountNonExpired()  { return true; }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked()   { return true; }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    @JsonIgnore
    public boolean isEnabled() { return true; }
}