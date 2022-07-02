package com.shubham.todolistapp.repository;

import com.shubham.todolistapp.entity.DaoUser;
import com.shubham.todolistapp.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {



    //method to get any single todo
    @Query("Select t From Todo t where t.todoId = :todoId and t.daoUser = :user")
    public Optional<Todo> findTodoOfUser(@Param("todoId") long todoId, @Param("user") Optional<DaoUser> user);


    @Query("Select t From Todo t where t.daoUser = :user")
    public Page<Todo> findTodosByDaoUser(@Param("user") Optional<DaoUser> daoUser, Pageable pageable);


}
