package com.rebellion.todo_list_api.service.ServiceInterface;

import org.springframework.http.ResponseEntity;

import com.rebellion.todo_list_api.dao.UserInDao;

public interface PublicService {
    ResponseEntity<?> registerUser(UserInDao input);
    ResponseEntity<?> loginUser(UserInDao input);
}
