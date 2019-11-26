package com.sda.springsecurity.converter;

import com.sda.springsecurity.dataModel.Role;
import com.sda.springsecurity.dto.RoleDTO;

public class RoleConverter {
    public static RoleDTO convert(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setUserId(role.getUserId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }
}
