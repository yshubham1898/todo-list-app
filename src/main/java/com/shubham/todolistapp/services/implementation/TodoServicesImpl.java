package com.shubham.todolistapp.services.implementation;

import com.shubham.todolistapp.entity.DaoUser;
import com.shubham.todolistapp.entity.Todo;
import com.shubham.todolistapp.repository.TodoRepository;
import com.shubham.todolistapp.repository.UserRepository;
import com.shubham.todolistapp.services.interfaces.TodoServices;
import com.shubham.todolistapp.services.interfaces.UserInfo;
import com.shubham.todolistapp.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
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
    UserInfo userInfo;


    //addTodo with user object
    @Override
    public Todo addTodo(Todo todo, long userId) {
        Optional<DaoUser> user = userRepository.findById(userId);
        if(user.isPresent()){
            todo.setDaoUser(user.get());
        }
        Todo savedTodo = todoRepository.save(todo);
        return savedTodo;
    }


    @Override
    public Optional<Todo> getTodo(long todoId)  {
        long userId = userInfo.getAuthUser().getUserId();
        Optional<DaoUser> user = userRepository.findById(userId);
        Optional<Todo> todo = todoRepository.findTodoOfUser(todoId,user);

        return todo;
    }

    @Override
    public Todo updateTodo(Todo todo, long todoId) {

        long userId = userInfo.getAuthUser().getUserId();
        Optional<DaoUser> user = userRepository.findById(userId);
//        Optional<Todo> todoToReplace = todoRepository.findTodoOfUser(todoId, user);

        if(user.isPresent()){
            todo.setDaoUser(user.get());
            todo.setTitle(todo.getTitle());
            todo.setContent(todo.getContent());
            todo.setDueDate(todo.getDueDate());
        }

        Todo updateTodo = todoRepository.save(todo);
        return updateTodo;
    }


    @Override
    public Page<Todo> getAllTodo(int offset, int pageSize) {
        Page<Todo> todos = null;
        long userId = userInfo.getAuthUser().getUserId();
        Optional<DaoUser> user = userRepository.findById(userId);

        if(user.isPresent()){
            Pageable pageable = PageRequest.of(offset,pageSize,Sort.by("createdDate").descending());
            todos = todoRepository.findTodosByDaoUser(user,pageable);
        }
        return todos;
    }


//    @Override
//    public List<Todo> getAllTodos() {
//        List<Todo> todos = null;
//        long userId = userInfo.getAuthUser().getUserId();
//        Optional<DaoUser> user = userRepository.findById(userId);
//        if(user.isPresent()){
//            todos = user.get().getTodos();
//        }
//        return todos;
//    }





    @Override
    public void deleteTodo(long todoId) {
        todoRepository.deleteById(todoId);
    }


}
