package com.itplatform.todo.service;

import com.itplatform.todo.domain.user.User;

import java.util.List;

public interface UserService {
    User findByName(String name) throws Exception;

    List<User> findByDel(boolean del);

    User save(User user);

    User toggleUserRole(String id) throws Exception;

    void delete(String id);
}
