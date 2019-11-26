package com.sda.springsecurity.service;

import com.sda.springsecurity.converter.RoleConverter;
import com.sda.springsecurity.dataModel.Role;
import com.sda.springsecurity.dto.RoleDTO;
import com.sda.springsecurity.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

//    public List<Role> findAllRoles() {
//        return roleRepository.findAll();
//    }
//
//    public List<RoleDTO> findAllRolesFormatted(Integer page, Integer size) {
//        List<Role> dbRoles = roleRepository.findAll(PageRequest.of(page, size)).getContent();
//        List<RoleDTO> roles = new ArrayList<>();
//        for(Role r : dbRoles) {
//            roles.add(RoleConverter.convert(r));
//        }
//        return roles;
//    }
//
//    public Role findById(Integer id) {
//        Optional<Role> roleById = roleRepository.findById(id);
//        if(roleById.isPresent()) {
//            return roleById.get();
//        }
//        return null;
//    }

    public List<Role> findByUserId(Integer userId) {
        return roleRepository.findByUserId(userId);
    }

//    public Role createRole(Role role) {
//        return roleRepository.save(role);
//    }
//
//    public Role updateRole(Integer id, Role role) {
//        Role temp = findById(id);
//        temp.setUserId(role.getUserId());
//        temp.setName(role.getName());
//        return roleRepository.save(temp);
//    }
//
//    public void deleteRole(Integer id) {
//        roleRepository.deleteById(id);
//    }
}
