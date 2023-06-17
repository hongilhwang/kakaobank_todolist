package com.itplatform.todo.service;

import com.itplatform.todo.domain.User;
import com.itplatform.todo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByName(String name) throws Exception {
        return userRepository.findByName(name).orElseThrow(() -> new Exception("존재하지 않는 사용자 입니다."));
    }

    public User toggleUserRole(String id) throws Exception {
        User user = userRepository.findById(Long.parseLong(id)).orElseThrow(() -> new Exception("존재하지 않는 사용자 입니다."));
        user.setAdmin(!user.isAdmin());
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
