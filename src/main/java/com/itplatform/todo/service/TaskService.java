package com.itplatform.todo.service;

import com.itplatform.todo.domain.Task;
import com.itplatform.todo.domain.User;

import java.util.List;

public interface TaskService {

    List<Task> findAll();

    Task findById(int id);

    Task save(Task task);

    Task update(Task task, User user);

    void deleteById(int id, User user);
}
