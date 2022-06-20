package com.shubham.todolistapp.controller;

import com.shubham.todolistapp.data.ResponseDto;
import com.shubham.todolistapp.entity.Todo;
import com.shubham.todolistapp.repository.TodoRepository;
import com.shubham.todolistapp.services.interfaces.TodoServices;
import com.shubham.todolistapp.services.interfaces.UserInfo;
import com.shubham.todolistapp.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/todo")
public class TodoController {

    @Autowired
    TodoServices todoServices;

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    UserInfo userInfo;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseDto addTodo(@RequestBody Todo todo) {
        long userId = userInfo.getAuthUser().getUserId();
//        return this.todoServices.addTodo(todo, userId);
        ResponseDto responseDto1 = new ResponseDto();
        return ResponseUtil.response(todoServices.addTodo(todo,userId),responseDto1.getTotalRecords(), responseDto1.getError());
    }


    @RequestMapping(value = "/update/{todoId}", method = RequestMethod.PATCH)
    public ResponseDto updateTodo(@RequestBody Todo todo, @PathVariable long todoId){

        ResponseDto responseDto1 = new ResponseDto();
        return ResponseUtil.response(todoServices.updateTodo(todo,todoId),responseDto1.getTotalRecords(),responseDto1.getError());


    }


    @RequestMapping(value = "/{todoId}",method = RequestMethod.GET)
    public ResponseDto getTodo(@PathVariable long todoId){
//        long userId = userInfo.getAuthUser().getUserId();
        Optional<Todo> todo = this.todoServices.getTodo(todoId);
        ResponseDto responseDto1 = new ResponseDto();
        return ResponseUtil.response(todo, responseDto1.getTotalRecords(),responseDto1.getError());
    }


//    @RequestMapping(value = "/all",method = RequestMethod.GET)
//    public List<Todo> getAllTodos(){
//        //get userId
////        long userId = userInfo.getAuthUser().getUserId();
//        //get all todos in List
//        List<Todo> todos = this.todoServices.getAllTodos();
//        return todos;
//    }


    //pagination
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseDto getAllTodos(@RequestParam(name = "offset", defaultValue = "0") int offset, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize){
        Page<Todo> pageTodo = this.todoServices.getAllTodo(offset, pageSize);
        ResponseDto responseDto1 = new ResponseDto();
        return ResponseUtil.response(pageTodo,pageTodo.getTotalElements(), responseDto1.getError());
    }







    @RequestMapping(value = "/{todoId}",method = RequestMethod.DELETE)
    public void deleteTodo(@PathVariable int todoId){
//        long userId = userInfo.getAuthUser().getUserId();
        this.todoServices.deleteTodo(todoId);
    }







}
