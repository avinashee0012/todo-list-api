package com.rebellion.todo_list_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rebellion.todo_list_api.dao.UserInDao;
import com.rebellion.todo_list_api.dao.UserOutDao;
import com.rebellion.todo_list_api.entity.User;
import com.rebellion.todo_list_api.repo.UserRepo;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/v1.0/api")
public class PublicController {

    private final UserRepo userRepo;

    PublicController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("")
    public HashMap<String, String> getApiDetails(HttpServletRequest request) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Status", "Running");
        map.put("URL", request.getRequestURL().toString());
        return map;
    }
    

    @PostMapping("/register")
    public UserOutDao registerUser(@RequestBody UserInDao input) {
        // TODO: /register : Error Handling (duplicate registration, other errors)
        User user = input.toUser();
        userRepo.save(user);
        return user.toUserOutDao();
    }

    // TODO: Login endpoint to authenticate the user and generate a token
    @GetMapping("/login")
    public String loginUser(@RequestBody UserInDao input) {
        // TODO: /register : Error Handling
        return new String();
    }
    
    
}
