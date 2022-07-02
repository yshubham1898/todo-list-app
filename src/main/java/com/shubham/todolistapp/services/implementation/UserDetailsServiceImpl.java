package com.shubham.todolistapp.services.implementation;

import com.shubham.todolistapp.data.TodoUser;
import com.shubham.todolistapp.entity.DaoUser;
import com.shubham.todolistapp.data.UserDto;
import com.shubham.todolistapp.repository.UserRepository;
import com.shubham.todolistapp.services.interfaces.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetails {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    //the method can be implemented to feed customer information to the spring security API
    @Override
    public TodoUser loadUserByUsername(String username) throws UsernameNotFoundException {

        //getting user from username
        DaoUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new TodoUser(user.getId(), user.getUsername(), user.getPassword());
    }


    public DaoUser save(UserDto user) {
        DaoUser newUser = new DaoUser();
        newUser.setUsername(user.getUsername());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }

    //to get principal from which we can extract a userId which is a foreign key
    public TodoUser getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null ? (TodoUser)auth.getPrincipal() : null;
    }


}
