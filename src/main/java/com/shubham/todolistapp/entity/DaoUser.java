package com.shubham.todolistapp.entity;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class DaoUser {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "daoUser", fetch = FetchType.LAZY)
    private List<Todo> todos;

    public DaoUser(long id, String username, String password, List<Todo> todos) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.todos = todos;
    }

    public DaoUser() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodo(List<Todo> todo) {
        this.todos = todos;
    }
}
