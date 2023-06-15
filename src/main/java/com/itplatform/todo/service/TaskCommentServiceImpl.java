package com.itplatform.todo.service;

import com.itplatform.todo.domain.Comment;
import com.itplatform.todo.domain.Task;
import com.itplatform.todo.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TaskCommentServiceImpl implements TaskCommentService {

    private final TaskRepository taskRepository;

    public TaskCommentServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional
    public Task save(int taskId, Comment comment) {
        // Make sure id is not set by user
        comment.setId(0);

        Task task = taskRepository.getOne(taskId);

        if (task == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not added");
        }

        task.addComment(comment);
        taskRepository.saveAndFlush(task);

        return task;
    }
}
