package com.mirai.indidea.controller;

import com.mirai.indidea.dao.UserRepository;
import com.mirai.indidea.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public List<UserEntity> user() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> user(@PathVariable int id) {
        return userRepository.findById(id);
    }

}
