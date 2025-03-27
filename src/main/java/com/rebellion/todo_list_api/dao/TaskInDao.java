package com.rebellion.todo_list_api.dao;

import com.rebellion.todo_list_api.entity.Task;

// TODO: Implement proper data validation
public class TaskInDao {
    private String title;
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
