package com.rebellion.todo_list_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rebellion.todo_list_api.entity.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long>{
    
}
