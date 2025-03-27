package com.rebellion.todo_list_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("")
public class InternalController {

    @GetMapping("")
    public HashMap<String, String> getApplicationStatus(HttpServletRequest request) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Status", "Running");
        map.put("URL", request.getRequestURL().toString());
        return map;
    }
}
