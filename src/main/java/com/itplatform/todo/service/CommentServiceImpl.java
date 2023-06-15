package com.itplatform.todo.service;

import com.itplatform.todo.domain.Comment;
import com.itplatform.todo.repository.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public Comment update(Comment comment) {
        if (!commentRepository.existsById(comment.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not updated");
        }

        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        commentRepository.deleteById(id);
    }
}
