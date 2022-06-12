package com.shubham.todolistapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "userTodo")
public class Todo {


    @Id
    @Column(name = "todo_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoId;

    private String title;

    private String content;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private DaoUser daoUser;

    public Todo(long todoId, String title, String content, DaoUser daoUser) {
        this.todoId = todoId;
        this.title = title;
        this.content = content;
        this.daoUser = daoUser;
    }

    public Todo() {

    }

    public long getTodoId() {
        return todoId;
    }

    public void setTodoId(long todoId) {
        this.todoId = todoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DaoUser getDaoUser() {
        return daoUser;
    }

    public void setDaoUser(DaoUser daoUser) {
        this.daoUser = daoUser;
    }


}
