package com.sda.springsecurity.controller;

import com.sda.springsecurity.dataModel.Role;
import com.sda.springsecurity.dto.RoleDTO;
import com.sda.springsecurity.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/roles")
public class RoleController {
//    @Autowired
//    private RoleService roleService;
//
//    @GetMapping(value = "/findAllFormatted")
//    public List<RoleDTO> findAllRolesFormatted(@RequestParam(value = "page", defaultValue = "0") Integer page,
//                                               @RequestParam(value = "size", defaultValue = "100") Integer size) {
//        return roleService.findAllRolesFormatted(page, size);
//    }
//
//    @GetMapping(value = "/findAll")
//    public List<Role> findAllRoles() {
//        return roleService.findAllRoles();
//    }
//
//    @GetMapping(value = "findById/{id}")
//    public Role findById(@PathVariable(name = "id") Integer id) {
//        return  roleService.findById(id);
//    }
//
//    @GetMapping(value = "findById/{name}")
//    public Role findByName(@PathVariable(name = "name") String name) {
//        return roleService.findByName(name);
//    }
//
//    @PostMapping
//    public Role createRole(@RequestBody Role role) {
//        return roleService.createRole(role);
//    }
//
//    @PutMapping(value = "/{id}")
//    public Role updateRole(@PathVariable(name = "id") Integer id, @RequestBody Role role) {
//        return roleService.updateRole(id, role);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public void deleteRole(@PathVariable(name = "id") Integer id) {
//        roleService.deleteRole(id);
//    }
}
