package com.rebellion.todo_list_api.dao;

import com.rebellion.todo_list_api.entity.Task;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class TaskInDao {

    @NotEmpty(message = "Title cannot be empty!")
    @Size(max = 30, message = "Title cannot be longer than 30 characters.")
    private String title;
    @NotEmpty(message = "Description cannot be empty!")
    @Size(max = 200, message = "Description cannot be longer than 200 characters.")
    private String description;

    public TaskInDao() {
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

    public Task toTask(){
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        return task;
    }

}
