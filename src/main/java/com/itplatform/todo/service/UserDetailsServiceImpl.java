package com.itplatform.todo.service;

import com.itplatform.todo.domain.User;
import com.itplatform.todo.auth.ToDoUserDetails;
import com.itplatform.todo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByName("username").orElseThrow(() -> new UsernameNotFoundException("유저를 찾지 못했습니다."));

        return new ToDoUserDetails(user);
    }
}
