package com.sda.springsecurity.controller;

import com.sda.springsecurity.dataModel.User;
import com.sda.springsecurity.dto.UserDTO;
import com.sda.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    /** old findAll method **/
    /** not to use two findAll methods!!! **/
    @GetMapping(value = "/findAll")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    /** final findAll method **/
    @GetMapping(value = "/findAllFormatted")
    public List<UserDTO> findAllUsersFormatted(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "50") Integer size,
                                               @RequestHeader(value = "Test-header", required = false) String testHeader) {
/*        if(true) {
            throw new RuntimeException();
        }*/

        System.out.println(testHeader);
        return userService.findAllUsersFormatted(page, size);
    }

    @GetMapping(value = "/findByUserName/{userName}")
    public User findByName(@PathVariable(name = "userName") String userName) {
        return  userService.findByUserName(userName);
    }

    @GetMapping(value = "/findByUserId/{id}")
    public User findById(@PathVariable(name = "id") Integer userId) {
        return userService.findById(userId);
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping (value = "/{id}")
    public User updateUser(@PathVariable(name = "id") Integer id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping (value = "/{id}")
    public void deleteUser(@PathVariable(name = "id") Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping (value = "/count")
    public long countUsers() {
        return userService.countUsers();
    }
}
