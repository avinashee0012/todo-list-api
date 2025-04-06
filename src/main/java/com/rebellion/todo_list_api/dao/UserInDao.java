package com.rebellion.todo_list_api.dao;

import com.rebellion.todo_list_api.entity.User;

import jakarta.validation.constraints.NotEmpty;

public class UserInDao {
    private String name = "John Doe";
    @NotEmpty(message = "Email cannot be empty!")
    private String email;
    @NotEmpty(message = "Password cannot be empty!")
    private String password;

    public UserInDao() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User toUser() {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

}
