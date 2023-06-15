package com.itplatform.todo.controller;

import com.itplatform.todo.domain.Comment;
import com.itplatform.todo.domain.Task;
import com.itplatform.todo.service.TaskCommentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskCommentController {

    private final TaskCommentService taskCommentService;

    public TaskCommentController(TaskCommentService taskCommentService) {
        this.taskCommentService = taskCommentService;
    }

    @PostMapping("/{taskId}/comments")
    public Task addComment(@PathVariable int taskId, @RequestBody Comment comment) {
        return taskCommentService.save(taskId, comment);
    }
}
