package com.rebellion.todo_list_api.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rebellion.todo_list_api.entity.Task;
import com.rebellion.todo_list_api.entity.User;


@Repository
public interface TaskRepo extends JpaRepository<Task, Long>{
    Page<Task> findByUser(Pageable pageable, User user);
    Optional<Task> findByUserAndId(User user, Long id);
}
