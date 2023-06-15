package com.itplatform.todo.service;

import com.itplatform.todo.domain.Comment;
import com.itplatform.todo.domain.Task;

public interface TaskCommentService {

    Task save(int taskId, Comment comment);
}
