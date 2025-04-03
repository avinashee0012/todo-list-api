package com.rebellion.todo_list_api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        if(isLoggedIn()) {
            User user = userRepo.findByEmail((String) session.getAttribute("todo_email"));
            if(user.getPassword().equals(token)){
                Pageable pageable = PageRequest.of(page.intValue(), limit.intValue()); // creates a request for requred page
                Page<Task> taskPage = taskRepo.findByUser(pageable, user); // A page is returned
                Page<TaskOutDao> taskDTOPage = taskPage.map(task -> task.toTaskOutDao()); // Task is converted to TaskOutDao to hide confidential information
                List<TaskOutDao> dataList = taskDTOPage.getContent(); // Page is converted to List

                HashMap<String, Object> map = new HashMap<>(); // could have used ModelMap as well
                map.put("data", dataList);
                map.put("page", page);
                map.put("limit", limit);
                map.put("total pages", taskPage.getTotalPages());

                return new ResponseEntity<>(map, HttpStatus.OK);
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
                Optional<Task> task = taskRepo.findByUserAndId(user, id);
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
                Optional<Task> task = taskRepo.findByUserAndId(user, id);
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
                Optional<Task> task = taskRepo.findByUserAndId(user, id);
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
