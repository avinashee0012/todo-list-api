package com.rebellion.todo_list_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rebellion.todo_list_api.dao.UserInDao;
import com.rebellion.todo_list_api.service.PublicServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/v1.0/api")
public class PublicController {

    private PublicServiceImpl publicServiceImpl;

    PublicController(PublicServiceImpl publicServiceImpl) {
        this.publicServiceImpl = publicServiceImpl;
    }

    @GetMapping("")
    public HashMap<String, String> getApiDetails(HttpServletRequest request) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Status", "Running");
        map.put("URL", request.getRequestURL().toString());
        return map;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserInDao input) {
        return publicServiceImpl.registerUser(input);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserInDao input) {
        return publicServiceImpl.loginUser(input);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        return publicServiceImpl.logoutUser();
    }

}
