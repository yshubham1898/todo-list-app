package com.shubham.todolistapp.controller;

import com.shubham.todolistapp.entity.Todo;
import com.shubham.todolistapp.services.TodoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Optional;

@Controller
public class TodoController {

    @Autowired
    TodoServices todoServices;


    @RequestMapping(value = "/addTodo", method = RequestMethod.POST)
    public Todo addTodo(@RequestBody Todo todo){
        return this.todoServices.addTodo(todo);
    }


    @RequestMapping(value = "/getAllTodos/{todoId}",method = RequestMethod.GET)
    public Optional<Todo> getTodo(@PathVariable int todoId){
        Optional<Todo> todo = this.todoServices.getTodo(todoId);
        return todo;
    }


    @RequestMapping(value = "/getAllTodos",method = RequestMethod.GET)
    public List<Todo> getAllTodos(){
        return this.todoServices.getAllTodos();
    }


    @RequestMapping(value = "/deleteTodo",method = RequestMethod.DELETE)
    public void deleteTodo(int todoId){
        this.todoServices.deleteTodo(todoId);
    }

}
