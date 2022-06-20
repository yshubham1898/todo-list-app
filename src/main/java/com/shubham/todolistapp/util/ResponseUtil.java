package com.shubham.todolistapp.util;

import com.shubham.todolistapp.data.ResponseDto;
import com.shubham.todolistapp.entity.Todo;
import com.shubham.todolistapp.repository.TodoRepository;
import com.shubham.todolistapp.services.interfaces.TodoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtil {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    TodoServices todoServices;


    public static <T>ResponseDto response(T data,long totalRecords,Error error){
        return new ResponseDto<>(data, totalRecords, error);
    }
}
