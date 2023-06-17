package com.itplatform.todo.service;

import com.itplatform.todo.domain.User;

import java.util.List;

public interface UserService {
    User findByName(String name) throws Exception;

    List<User> findAll();

    User toggleUserRole(String id) throws Exception;
}
