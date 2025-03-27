package com.rebellion.todo_list_api.dao;

public class TaskOutDao {
    private String title;
    private String description;
    private UserOutDao user;

    public TaskOutDao() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserOutDao getUser() {
        return user;
    }

    public void setUser(UserOutDao user) {
        this.user = user;
    }

}
