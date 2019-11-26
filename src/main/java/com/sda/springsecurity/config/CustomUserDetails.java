package com.sda.springsecurity.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails extends User {
    private final Integer id;
    private final String firstName;
    private final String lastName;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
                            Integer id, String firstName, String lastName) {
        super(username, password, authorities);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
