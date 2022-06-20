package com.shubham.todolistapp.services.interfaces;

import com.shubham.todolistapp.entity.Todo;
import org.springframework.data.domain.Page;

import javax.swing.text.html.Option;
import java.util.Optional;


public interface TodoServices {

    //addApi
    public Todo addTodo(Todo todo, long userId);


    //get single todoApi
    public Optional<Todo> getTodo(long todoId);


    //pagination getAll Api
    public Page<Todo> getAllTodo(int offset, int pageSize);


    //updateApi --> pending
    public Todo updateTodo(Todo todo, long todoId);


    //deleteApi
    public void deleteTodo(long todoId);

}
