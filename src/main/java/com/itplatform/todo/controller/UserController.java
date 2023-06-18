package com.itplatform.todo.controller;


import com.itplatform.todo.domain.User;
import com.itplatform.todo.service.UserService;
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
    public List<User> getUsers() {
        return userService.findByDel(false);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") String id) throws Exception {

        return userService.toggleUserRole(id);
    }

    @PostMapping("")
    public User createUser(@RequestBody User user) {

        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") String id) {
        userService.delete(id);
        return id;
    }

}
