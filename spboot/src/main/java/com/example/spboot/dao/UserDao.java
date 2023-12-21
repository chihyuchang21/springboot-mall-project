package com.example.spboot.dao;

import com.example.spboot.dto.UserRegisterRequest;
import com.example.spboot.model.User;
import org.springframework.stereotype.Component;

@Component
public interface UserDao {

    User getUserById(Integer userId);

    User getUserByEmail(String email);

    Integer createUser(UserRegisterRequest userRegisterRequest);
}
