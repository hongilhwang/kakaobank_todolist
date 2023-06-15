package com.itplatform.todo.service;

import com.itplatform.todo.domain.Comment;

public interface CommentService {

    Comment update(Comment comment);

    void deleteById(int id);
}
