package com.shubham.todolistapp.services.interfaces;

import com.shubham.todolistapp.entity.Todo;

import java.util.List;
import java.util.Optional;


public interface TodoServices {

    public Todo addTodo(Todo todo, long userId);

    //addTodo with user object


    Optional<Todo> getTodo(long todoId, long userId);

    public List<Todo> getAllTodos(long userId);


    public void deleteTodo(long todoId);


}
