package com.shubham.todolistapp.services;

import com.shubham.todolistapp.entity.Todo;

import java.util.List;
import java.util.Optional;


public interface TodoServices {

    public Todo addTodo(Todo todo);

    public List<Todo> getAllTodos();

    public Optional<Todo> getTodo(int todoId);

    public void deleteTodo(int todoId);

}
