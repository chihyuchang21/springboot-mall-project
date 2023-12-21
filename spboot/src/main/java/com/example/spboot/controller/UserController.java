package com.example.spboot.controller;

import com.example.spboot.dto.UserRegisterRequest;
import com.example.spboot.model.User;
import com.example.spboot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        Integer userId = userService.register(userRegisterRequest); //userId會接住register func傳回來的

        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user); //回傳201狀態碼
    }
}
