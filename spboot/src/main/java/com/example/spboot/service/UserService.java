package com.example.spboot.service;

import com.example.spboot.dto.UserRegisterRequest;
import com.example.spboot.model.User;

public interface UserService {

    User getUserById(Integer userId);
    Integer register(UserRegisterRequest userRegisterRequest);
}
