package com.shubham.todolistapp.services.implementation;

import com.shubham.todolistapp.entity.DaoUser;
import com.shubham.todolistapp.entity.Todo;
import com.shubham.todolistapp.repository.TodoRepository;
import com.shubham.todolistapp.repository.UserRepository;
import com.shubham.todolistapp.services.interfaces.TodoServices;
import com.shubham.todolistapp.services.interfaces.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static javax.swing.UIManager.get;

@Service
public class TodoServicesImpl implements TodoServices {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserInfo userInfo;


    //addTodo with user object
    @Override
    public Todo addTodo(Todo todo, long userId) {
        Optional<DaoUser> user = userRepository.findById(userId);
        if(user.isPresent()){
            todo.setDaoUser(user.get());
        }
        todoRepository.save(todo);
        return todo;
    }

    @Override
    public Optional<Todo> getTodo(long todoId, long userId)  {

        Optional<DaoUser> user = userRepository.findById(userId);

        Optional<Todo> todo = todoRepository.findTodoOfUser(todoId,user);

        return todo;

    }


    @Override
    public List<Todo> getAllTodos(long userId) {
        List<Todo> todos = null;
        Optional<DaoUser> user = userRepository.findById(userId);
        if(user.isPresent()){
            todos = user.get().getTodo();
        }
        return todos;
    }



    @Override
    public void deleteTodo(long todoId) {
//        Optional<DaoUser> user = userRepository.findById(userId);
//        //check if user is present
//        if(user.isPresent()){
//            todoRepository.deleteById(todoId);
//        }
        todoRepository.deleteById(todoId);
    }
}
