package com.shubham.todolistapp.controller;

import com.shubham.todolistapp.entity.DaoUser;
import com.shubham.todolistapp.entity.Todo;
import com.shubham.todolistapp.services.interfaces.TodoServices;
import com.shubham.todolistapp.services.interfaces.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/todo")
public class TodoController {

    @Autowired
    TodoServices todoServices;

    @Autowired
    UserInfo userInfo;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Todo addTodo(@RequestBody Todo todo) {
        long userId = userInfo.getAuthUser().getUserId();
        return this.todoServices.addTodo(todo, userId);
    }




    @RequestMapping(value = "/{todoId}",method = RequestMethod.GET)
    public Optional<Todo> getTodo(@PathVariable long todoId){
        long userId = userInfo.getAuthUser().getUserId();
        Optional<Todo> todo = this.todoServices.getTodo(todoId, userId);
        return todo;
    }


    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<Todo> getAllTodos(){
        //get userId
        long userId = userInfo.getAuthUser().getUserId();
        //get all todos in List
        List<Todo> todos = this.todoServices.getAllTodos(userId);
        return todos;
    }



    @RequestMapping(value = "/{todoId}",method = RequestMethod.DELETE)
    public void deleteTodo(@PathVariable int todoId){
//        long userId = userInfo.getAuthUser().getUserId();
        this.todoServices.deleteTodo(todoId);
    }



}
