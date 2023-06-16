package com.itplatform.todo.service;

import com.itplatform.todo.domain.User;

public interface UserService {
    User findByName(String name) throws Exception;
}
