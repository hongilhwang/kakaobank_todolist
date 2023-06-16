package com.itplatform.todo.controller;

import com.itplatform.todo.auth.LoginProvider;
import com.itplatform.todo.domain.LoginUser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class LoginController {

    private final LoginProvider loginProvider;

    public LoginController(LoginProvider loginProvider) {
        this.loginProvider = loginProvider;
    }

    @PostMapping("/api/login")
    public ResponseEntity<LoginUser> login(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization) {
        String basicAuth64Encoding = authorization.split(" ")[1];
        String basicAuth = new String(Base64.getDecoder().decode(basicAuth64Encoding));
        String[] idAndPw = basicAuth.split(":");

        return new ResponseEntity<LoginUser>(loginProvider.login(idAndPw[0], idAndPw[1]), HttpStatus.OK);
    }
}
