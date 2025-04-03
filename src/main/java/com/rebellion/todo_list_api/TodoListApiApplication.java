package com.rebellion.todo_list_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class TodoListApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoListApiApplication.class, args);
	}
}
