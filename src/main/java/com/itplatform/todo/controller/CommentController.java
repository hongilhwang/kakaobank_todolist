package com.itplatform.todo.controller;

import com.itplatform.todo.domain.Comment;
import com.itplatform.todo.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PutMapping("")
    public Comment updateComment(@RequestBody Comment comment) {
        commentService.update(comment);

        return comment;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteComment(@PathVariable int id) {

        commentService.deleteById(id);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
