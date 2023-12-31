package com.itplatform.todo.service;

import com.itplatform.todo.domain.user.User;
import com.itplatform.todo.repository.user.UserRepository;
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

    @Override
    public User toggleUserRole(String id) throws Exception {
        User user = userRepository.findById(Long.parseLong(id)).orElseThrow(() -> new Exception("존재하지 않는 사용자 입니다."));
        user.setAdmin(!user.isAdmin());
        return userRepository.save(user);
    }

    @Override
    public void delete(String id) {
        User user = userRepository.findById(Long.parseLong(id)).orElse(new User());
        if (user.getId() != null) {
            user.setDel(true);
            userRepository.save(user);
        }
    }

    @Override
    public List<User> findByDel(boolean del) {
        return userRepository.findByDel(del);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
