package com.rebellion.todo_list_api.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rebellion.todo_list_api.config.CustomPasswordEncoder;
import com.rebellion.todo_list_api.dao.UserInDao;
import com.rebellion.todo_list_api.dao.UserOutDao;
import com.rebellion.todo_list_api.entity.User;
import com.rebellion.todo_list_api.repo.UserRepo;
import com.rebellion.todo_list_api.service.ServiceInterface.PublicService;

import jakarta.servlet.http.HttpSession;

@Service
public class PublicServiceImpl implements PublicService{

    private final UserRepo userRepo;
    private final HttpSession session;

    PublicServiceImpl(UserRepo userRepo, HttpSession session) {
        this.userRepo = userRepo;
        this.session = session;
    }

    @Override
    public ResponseEntity<?> registerUser(UserInDao input) {
        if (userRepo.findByEmail(input.getEmail()) == null) {
            User user = input.toUser();
            userRepo.save(user);
            return new ResponseEntity<UserOutDao>(user.toUserOutDao(), HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("Invalid Registration", HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    public ResponseEntity<?> loginUser(UserInDao input) {
        User user = userRepo.findByEmail(input.getEmail());
        if (user != null) {
            CustomPasswordEncoder encoder = new CustomPasswordEncoder();
            if (encoder.matches(input.getPassword(), user.getPassword())) {
                session.invalidate();
                session.setAttribute("todo_email", user.getEmail());
                return new ResponseEntity<UserOutDao>(user.toUserOutDao(), HttpStatus.FOUND);
            }  
        } else {
            return new ResponseEntity<String>("User not found with this email.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("Invalid Login", HttpStatus.NOT_ACCEPTABLE);
    }

}
