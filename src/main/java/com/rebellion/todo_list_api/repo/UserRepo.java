package com.rebellion.todo_list_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rebellion.todo_list_api.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
    User findByEmail(String email);
}
