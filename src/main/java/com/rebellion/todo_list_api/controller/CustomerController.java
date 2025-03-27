package com.rebellion.todo_list_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.rebellion.todo_list_api.dao.TaskInDao;
import com.rebellion.todo_list_api.service.CustomerServiceimpl;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/v1.0/api/user")
public class CustomerController {

    private final CustomerServiceimpl customerServiceimpl;

    CustomerController(CustomerServiceimpl customerServiceimpl) {
        this.customerServiceimpl = customerServiceimpl;
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> createTask(@RequestHeader(required = false) String token, @Valid @RequestBody TaskInDao input) {
        if (token != null) {
            return customerServiceimpl.createTask(token, input);
        }
        return new ResponseEntity<>("Token missing!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/tasks")
    public ResponseEntity<?> getTasks(@RequestHeader(required = false) String token, @RequestParam Long page, @RequestParam Long limit) {
        if (token != null) {
            return customerServiceimpl.getTasks(token, page, limit);
        }
        return new ResponseEntity<>("Token missing!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> getTaskById(@RequestHeader(required = false) String token, @PathVariable Long id) {
        if (token != null) {
            return customerServiceimpl.getTaskById(token, id);
        }
        return new ResponseEntity<>("Token missing!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<?> updateTaskById(@RequestHeader(required = false) String token, @PathVariable Long id, @RequestBody TaskInDao input) {
        if (token != null) {
            return customerServiceimpl.updateTaskById(token, id, input);
        }
        return new ResponseEntity<>("Token missing!", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteTaskById(@RequestHeader(required = false) String token, @PathVariable Long id) {
        if (token != null) {
            return customerServiceimpl.deleteTaskById(token, id);
        }
        return new ResponseEntity<>("Token missing!", HttpStatus.BAD_REQUEST);
    }
}
