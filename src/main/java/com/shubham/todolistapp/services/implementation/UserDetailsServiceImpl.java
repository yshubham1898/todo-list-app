package com.shubham.todolistapp.services.implementation;

import com.shubham.todolistapp.data.TodoUser;
import com.shubham.todolistapp.entity.DaoUser;
import com.shubham.todolistapp.entity.UserDto;
import com.shubham.todolistapp.repository.UserRepository;
import com.shubham.todolistapp.services.interfaces.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserInfo {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public TodoUser loadUserByUsername(String username) throws UsernameNotFoundException {

        DaoUser user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new TodoUser(user.getId(), user.getUsername(), user.getPassword());

    }


    public DaoUser save(UserDto user) {
        DaoUser newUser = new DaoUser();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }

    public TodoUser getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null ? (TodoUser)auth.getPrincipal() : null;
    }

}
