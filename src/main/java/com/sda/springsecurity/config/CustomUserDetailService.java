package com.sda.springsecurity.config;

import com.sda.springsecurity.dataModel.Role;
import com.sda.springsecurity.dataModel.User;
import com.sda.springsecurity.service.RoleService;
import com.sda.springsecurity.service.UserService;
import com.sda.springsecurity.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);
        if(user == null) {
            throw new UserNotFoundException("User not found");
        }

        /** password not encoded **/
        /** DaoAuthenticationProvider always sets the encoded password and is compared to user.getPassword()
        so user.getPassword() needs to be encoded also **/
        /** But we encoded the password when saving the user**/
        String password = user.getPassword();

        /** encoded password with @Autowired BCryptPasswordEncoder **/
        // String password = encoder.encode(user.getPassword());

        /** roles hardcoded **/
        // List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");

        List<Role> roles = roleService.findByUserId(user.getId());
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for(Role r : roles) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(r.getName());
            authorityList.add(grantedAuthority);
        }

        /** returns a User from spring **/
        // UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, password, authorityList);

        /** we extended the User class - CustomUserDetails, so: **/
        UserDetails userDetails = new CustomUserDetails(username, password, authorityList, user.getId(), user.getFirstName(), user.getLastName());
        return userDetails;
    }
}
