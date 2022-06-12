package com.shubham.todolistapp.services.interfaces;

import com.shubham.todolistapp.data.TodoUser;
import com.shubham.todolistapp.entity.DaoUser;
import com.shubham.todolistapp.entity.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserInfo extends UserDetailsService {
    DaoUser save(UserDto user);

    TodoUser getAuthUser();

}
