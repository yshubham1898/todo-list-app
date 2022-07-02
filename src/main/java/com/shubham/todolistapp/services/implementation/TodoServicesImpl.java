package com.shubham.todolistapp.services.implementation;

import com.shubham.todolistapp.entity.DaoUser;
import com.shubham.todolistapp.entity.Todo;
import com.shubham.todolistapp.repository.TodoRepository;
import com.shubham.todolistapp.repository.UserRepository;
import com.shubham.todolistapp.services.interfaces.TodoServices;
import com.shubham.todolistapp.services.interfaces.UserDetails;
import com.shubham.todolistapp.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TodoServicesImpl implements TodoServices {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    UserDetails userDetails;


    //addTodo with user object
    @Override
    public Todo addTodo(Todo todo, long userId) {
        Optional<DaoUser> user = userRepository.findById(userId);
        if(user.isPresent()){
            todo.setDaoUser(user.get());
        }
        return todoRepository.save(todo);
    }




    @Override
    public Optional<Todo> getTodo(long todoId)  {
        long userId = userDetails.getAuthUser().getUserId();
        Optional<DaoUser> user = userRepository.findById(userId);
        Optional<Todo> todo = todoRepository.findTodoOfUser(todoId,user);

        return todo;
    }


    @Override
    public Todo updateTodo(Todo todo, long todoId) {

        long userId = userDetails.getAuthUser().getUserId();
        Optional<DaoUser> user = userRepository.findById(userId);
        Optional<Todo> t = todoRepository.findTodoOfUser(todoId,user);

        if(user.isPresent()){
            todo.setTitle(todo.getTitle());
            todo.setContent(todo.getContent());
            todo.setDueDate(todo.getDueDate());
        }

        return todoRepository.save(todo);
    }


    @Override
    public Page<Todo> getAllTodo(int offset, int pageSize) {
        Page<Todo> todos = null;
        long userId = userDetails.getAuthUser().getUserId();
        Optional<DaoUser> user = userRepository.findById(userId);

        if(user.isPresent()){
            Pageable pageable = PageRequest.of(offset,pageSize,Sort.by("createdDate").descending());
            todos = todoRepository.findTodosByDaoUser(user,pageable);
        }
        return todos;
    }



    @Override
    public void deleteTodo(long todoId) {
        todoRepository.deleteById(todoId);

    }


}
