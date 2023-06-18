package com.itplatform.todo.controller;

import com.itplatform.todo.auth.ToDoUserDetails;
import com.itplatform.todo.domain.Task;
import com.itplatform.todo.service.TaskService;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public List<Task> findAll() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable int id) {
        return taskService.findById(id);
    }

    @PostMapping("")
    public Task addTask(@RequestBody Task task, @AuthenticationPrincipal ToDoUserDetails userDetails) {
        task.setReporter(userDetails.user().getName());
        return taskService.save(task);
    }

    @PutMapping("")
    public Task updateTask(@RequestBody Task task, @AuthenticationPrincipal ToDoUserDetails userDetails) {

        return taskService.update(task, userDetails.user());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable int id, @AuthenticationPrincipal ToDoUserDetails userDetails) {

        taskService.deleteById(id, userDetails.user());

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
