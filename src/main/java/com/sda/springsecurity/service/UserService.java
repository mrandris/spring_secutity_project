package com.sda.springsecurity.service;

import com.sda.springsecurity.converter.UserConverter;
import com.sda.springsecurity.dataModel.User;
import com.sda.springsecurity.dto.UserDTO;
import com.sda.springsecurity.repository.UserRepository;
import com.sda.springsecurity.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /** old findAll method **/
    /** not to use two findAll methods!!! **/
    public List<User> findAllUsers() {
       return userRepository.findAll();
    }

    /** intermediate findAll method **/
    /* public List<User> findAllUsers() {
        return userRepository.findAll(PageRequest.of(1, 1)).getContent();
    }*/

    /** final findAll method **/
    public List<UserDTO> findAllUsersFormatted(Integer page, Integer size) {
        List<User> dbUsers = userRepository.findAll(PageRequest.of(page, size)).getContent();
        List<UserDTO> users = new ArrayList<>();
        for(User u : dbUsers) {
            users.add(UserConverter.convert(u));
        }
        return users;
    }

    public User findByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        if(user == null) {
            throw new UserNotFoundException(String.format("User with username %s not found %s.", userName, " Please enter valid username!"));
        }
        return user;
    }

    public User findById(Integer userId) {
        if(userRepository.findById(userId).isPresent()) {
            return userRepository.findById(userId).get();
        } else {
            throw new UserNotFoundException(String.format("User with userId" + userId + " not found %s.", " Please enter valid userId!"));
        }
    }

    public User create(User user) {
        /** same password encoder as in SecurityConfig **/
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User user) {
        User temp = findById(id);
        temp.setUserName(user.getUserName());
        temp.setPassword(user.getPassword());
        temp.setFirstName(user.getFirstName());
        temp.setLastName(user.getLastName());
        return userRepository.save(temp);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public long countUsers() {
        return userRepository.count();
    }
}
