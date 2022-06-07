package com.shubham.todolistapp.repository;

import com.shubham.todolistapp.entity.DaoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<DaoUser, Integer> {

    DaoUser findByUsername(String username);
}
