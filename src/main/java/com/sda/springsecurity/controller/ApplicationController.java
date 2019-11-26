package com.sda.springsecurity.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/rest")
    public String currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();

        String roles = "";
        for(GrantedAuthority simpleGrantedAuthority : authentication.getAuthorities()) {
            roles = roles + simpleGrantedAuthority.getAuthority() + " ";
        }

        return "Welcome " + principal.getUsername() + " you have the following roles: " + roles;
        // principal.getPassword() returns a spring which is null for security reasons :)))
    }
}
