package com.itplatform.todo.service;

import com.itplatform.todo.domain.task.Comment;
import com.itplatform.todo.domain.task.Task;

public interface TaskCommentService {

    Task save(int taskId, Comment comment);
}
