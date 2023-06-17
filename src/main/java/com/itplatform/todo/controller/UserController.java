package com.itplatform.todo.controller;


import com.itplatform.todo.domain.User;
import com.itplatform.todo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userServiceImpl){
        this.userService = userServiceImpl;
    }

    @GetMapping("/api/admin/users")
    public ResponseEntity<List<User>> getUsers() {

        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/api/admin/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id) throws Exception {

        return new ResponseEntity<>(userService.toggleUserRole(id), HttpStatus.OK);
    }
}
