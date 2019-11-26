package com.sda.springsecurity.converter;

import com.sda.springsecurity.dataModel.User;
import com.sda.springsecurity.dto.UserDTO;

public class UserConverter {
    public static UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUserName());
        userDTO.setId(user.getId());
        userDTO.setPassword("*****");
        return userDTO;
    }

    public static User convertBack(UserDTO userDTO) {
        User user = new User();
        user.setPassword(userDTO.getPassword());
        user.setLastName(userDTO.getLastName());
        user.setFirstName(userDTO.getFirstName());
        user.setUserName(userDTO.getUsername());

        return user;
    }
}
