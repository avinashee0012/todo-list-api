package com.rebellion.todo_list_api.service.ServiceInterface;

import org.springframework.http.ResponseEntity;

import com.rebellion.todo_list_api.dao.TaskInDao;

public interface CustomerService {
    ResponseEntity<?> createTask(String token, TaskInDao input);
    ResponseEntity<?> getTasks(String token, Long page, Long limit);
    ResponseEntity<?> getTaskById(String token, Long id);
    ResponseEntity<?> updateTaskById(String token, Long id, TaskInDao input);
    ResponseEntity<?> deleteTaskById(String token, Long id);
}
