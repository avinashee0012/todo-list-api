package com.rebellion.todo_list_api.entity;

import java.util.List;

import com.rebellion.todo_list_api.config.CustomPasswordEncoder;
import com.rebellion.todo_list_api.dao.UserOutDao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column(unique = true)
    private String email;
    @Column
    private String password;

    @OneToMany
    private List<Task> tasks;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.password = new CustomPasswordEncoder().encode(password);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public UserOutDao toUserOutDao() {
        UserOutDao dao = new UserOutDao();
        dao.setToken(password);
        return dao;     
    }
}
