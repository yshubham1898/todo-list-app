package com.shubham.todolistapp.services;

import com.shubham.todolistapp.entity.Todo;
import com.shubham.todolistapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServicesImpl implements TodoServices{

    @Autowired
    private TodoRepository todoRepository;


    @Override
    public Todo addTodo(Todo todo) {
        todoRepository.save(todo);
        return todo;
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Optional<Todo> getTodo(int todoId) {
        Optional<Todo> todo = todoRepository.findById(todoId);
        return todo;
    }

    @Override
    public void deleteTodo(int todoId) {
        todoRepository.deleteById(todoId);
    }
}
