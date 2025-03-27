package com.rebellion.todo_list_api.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rebellion.todo_list_api.dao.PaginationDao;
import com.rebellion.todo_list_api.dao.TaskInDao;
import com.rebellion.todo_list_api.dao.TaskOutDao;
import com.rebellion.todo_list_api.entity.Task;
import com.rebellion.todo_list_api.entity.User;
import com.rebellion.todo_list_api.repo.TaskRepo;
import com.rebellion.todo_list_api.repo.UserRepo;
import com.rebellion.todo_list_api.service.ServiceInterface.CustomerService;

import jakarta.servlet.http.HttpSession;

@Service
public class CustomerServiceimpl implements CustomerService {

    private final TaskRepo taskRepo;
    private final UserRepo userRepo;
    private final HttpSession session;

    CustomerServiceimpl(TaskRepo taskRepo, UserRepo userRepo, HttpSession session) {
        this.taskRepo = taskRepo;
        this.session = session;
        this.userRepo = userRepo;
    }

    public boolean isLoggedIn(){
        String loggedin_email = (String) session.getAttribute("todo_email");
        if(loggedin_email != null) {
            return true;
        }
        return false;
    }

    @Override
    public ResponseEntity<?> createTask(String token, TaskInDao input) {
        Task task = input.toTask();
        if(isLoggedIn()){
            User user = userRepo.findByEmail((String) session.getAttribute("todo_email"));
            if(user.getPassword().equals(token)){
                task.setUser(user);
                taskRepo.save(task);
                return new ResponseEntity<TaskOutDao>(task.toTaskOutDao(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Not authorized!", HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<String>("Unauthorized Access", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> getTasks(String token, Long page, Long limit) {
        // TODO: Need to fix getTasks(String token, Long page, Long limit)
        if(isLoggedIn()) {
            User user = userRepo.findByEmail((String) session.getAttribute("todo_email"));
            if(user.getPassword().equals(token)){
                PaginationDao paginationDao = new PaginationDao();
                // Implement pagination and filtering for the to-do list
                return new ResponseEntity<>(paginationDao, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not authorized!", HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<String>("Unauthorized Access", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> getTaskById(String token, Long id) {
        if(isLoggedIn()) {
            User user = userRepo.findByEmail((String) session.getAttribute("todo_email"));
            if(user.getPassword().equals(token)){
                Optional<Task> task = taskRepo.findById(id);
                if(task.isPresent()) {
                    return new ResponseEntity<>(task.get().toTaskOutDao(), HttpStatus.FOUND);
                }
                return new ResponseEntity<>("No task found with Id: " + id, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>("Not authorized!", HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<String>("Unauthorized Access", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> updateTaskById(String token, Long id, TaskInDao input) {
        if(isLoggedIn()) {
            User user = userRepo.findByEmail((String) session.getAttribute("todo_email"));
            if(user.getPassword().equals(token)){
                Optional<Task> task = taskRepo.findById(id);
                if(task.isPresent()) {
                    Task dbTask = task.get();
                    dbTask.setTitle(input.getTitle());
                    dbTask.setDescription(input.getDescription());
                    taskRepo.save(dbTask);
                    return new ResponseEntity<>(dbTask.toTaskOutDao(), HttpStatus.CREATED);
                }
                return new ResponseEntity<>("No task found with Id: " + id, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>("Not authorized!", HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<String>("Unauthorized Access", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> deleteTaskById(String token, Long id) {
        if(isLoggedIn()) {
            User user = userRepo.findByEmail((String) session.getAttribute("todo_email"));
            if(user.getPassword().equals(token)){
                Optional<Task> task = taskRepo.findById(id);
                if(task.isPresent()) {
                    taskRepo.deleteById(id);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>("No task found with Id: " + id, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>("Not authorized!", HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<String>("Unauthorized Access", HttpStatus.UNAUTHORIZED);
    }

}
