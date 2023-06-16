package com.itplatform.todo.auth;

import com.itplatform.todo.domain.LoginUser;
import com.itplatform.todo.service.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginProvider {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    public LoginProvider(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    public LoginUser login(String id, String pw) throws BadCredentialsException {
        LoginUser loginUser = new LoginUser();
        try {
            loginUser.setUser(userService.findByName(id));
        } catch (Exception e) {
            // Not found user.
            throw new BadCredentialsException(e.getMessage());
        }

        if (!loginUser.getUser().getPw().equals(pw)) {
            throw new BadCredentialsException("패스워드가 잘못 되었습니다.");
        }

        List<String> roles = new java.util.ArrayList<>(List.of("USER"));
        if (loginUser.getUser().isAdmin())
            roles.add("ADMIN");

        loginUser.setToken(jwtProvider.createToken(loginUser.getUser().getName(), roles));
        return loginUser;
    }
}
