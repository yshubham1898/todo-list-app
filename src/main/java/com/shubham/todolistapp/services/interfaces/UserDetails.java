package com.shubham.todolistapp.services.interfaces;

import com.shubham.todolistapp.data.TodoUser;
import com.shubham.todolistapp.entity.DaoUser;
import com.shubham.todolistapp.data.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetails extends UserDetailsService {
    DaoUser save(UserDto user);

    TodoUser getAuthUser();



}
