package com.shubham.todolistapp.repository;

import com.shubham.todolistapp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

}
