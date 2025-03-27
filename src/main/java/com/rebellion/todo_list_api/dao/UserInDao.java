package com.rebellion.todo_list_api.dao;

import com.rebellion.todo_list_api.entity.User;

// TODO: Implement proper data validation
public class UserInDao {
    private String name;
    private String email;
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
