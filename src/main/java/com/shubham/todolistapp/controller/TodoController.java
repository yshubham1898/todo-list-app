package com.shubham.todolistapp.controller;

import com.shubham.todolistapp.data.ResponseDto;
import com.shubham.todolistapp.entity.Todo;
import com.shubham.todolistapp.repository.TodoRepository;
import com.shubham.todolistapp.services.interfaces.TodoServices;
import com.shubham.todolistapp.services.interfaces.UserDetails;
import com.shubham.todolistapp.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    UserDetails userDetails;



    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseDto addTodo(@RequestBody Todo todo) {
        long userId = userDetails.getAuthUser().getUserId();
        ResponseDto responseDto1 = new ResponseDto();
        return ResponseUtil.response(todoServices.addTodo(todo,userId),responseDto1.getTotalRecords(), responseDto1.getError());
    }


    @RequestMapping(value = "/update/{todoId}", method = RequestMethod.PUT)
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
    public ResponseEntity<String> deleteTodo(@PathVariable int todoId){


        todoServices.deleteTodo(todoId);
        return new ResponseEntity<String>("Todo deleted successfully!!!", HttpStatus.OK);
    }







}
