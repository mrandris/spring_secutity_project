package com.sda.springsecurity.controller;

import com.sda.springsecurity.config.CustomUserDetails;
import com.sda.springsecurity.converter.UserConverter;
import com.sda.springsecurity.dataModel.User;
import com.sda.springsecurity.dto.UserDTO;
import com.sda.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UIController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/index")
    public String index(Model model) {

        /** error if securityConfig HttpSecurity is set http.permitAll()**/
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails principal =  (CustomUserDetails) authentication.getPrincipal();

        /** adding only some attributes **/
        model.addAttribute("firstName", principal.getFirstName());
        model.addAttribute("lastName", principal.getLastName());

        /** passing the whole object and accessing attribute from html **/
        model.addAttribute("principal", principal);

        return "index";
    }

    @GetMapping(value = "/users")
    public String users(Model model) {
        List<UserDTO> users = userService.findAllUsersFormatted(0,50);
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("user", new UserDTO());
//        User user = new User(model.getAttribute());
//        userService.create(user);

        return "register";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute(name = "user") UserDTO userDTO) {
//        System.out.println(userDTO);
        try {
            User user = UserConverter.convertBack(userDTO);
            userService.create(user);
        } catch (Exception e) {
            return "redirect:register?error";
        }

        return "redirect:register?success";
    }

    /** ovidiu trifan email address **/
    /** ovidiu.trifan01@gmail.com **/

    /** trying to update user **/
    /** not working yet **/
    /** messing with edit.html **/

    /** first: edit with GetMapping to get the info then something with PostMapping to update the user **/

    @PostMapping(value = "/user/{userId}")
    public String edit(@ModelAttribute(name = "user") UserDTO userDTO, @PathVariable(name = "userId") Integer userId) {
//        System.out.println(userDTO);
        try {
            User user = UserConverter.convertBack(userDTO);
            userService.updateUser(userId, user);
        } catch (Exception e) {
            return "redirect:edit?error";
        }

        return "redirect:edit?success";
    }
}
