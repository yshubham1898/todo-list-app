package com.shubham.todolistapp.repository;

import com.shubham.todolistapp.entity.DaoUser;
import com.shubham.todolistapp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<DaoUser, Long> {

    DaoUser findByUsername(String username);

    Optional<DaoUser> findById(long id);

}
