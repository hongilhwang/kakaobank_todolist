package com.itplatform.todo.controller;


import com.itplatform.todo.domain.User;
import com.itplatform.todo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userServiceImpl){
        this.userService = userServiceImpl;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.findByDel(false), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id) throws Exception {

        return new ResponseEntity<>(userService.toggleUserRole(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<User> updateUser(@RequestBody User user) {

        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        userService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
