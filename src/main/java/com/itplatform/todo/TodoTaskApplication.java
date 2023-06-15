package com.itplatform.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.itplatform.todo.domain")
public class TodoTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoTaskApplication.class, args);
    }
}
