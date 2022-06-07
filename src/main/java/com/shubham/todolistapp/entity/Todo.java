package com.shubham.todolistapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "userTodo")
public class Todo {


    @Id
    @Column(name = "id")
    private int todoId;

    private String title;

    private String content;

    private Boolean status;

    private Date date;

    public Todo(int todoId, String title, String content, Boolean status, Date date) {
        this.todoId = todoId;
        this.title = title;
        this.content = content;
        this.status = status;
        this.date = date;
    }

    public Todo() {

    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "todoId=" + todoId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", date=" + date +
                '}';
    }
}
