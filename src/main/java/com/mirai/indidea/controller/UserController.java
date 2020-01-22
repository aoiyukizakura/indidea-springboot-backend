package com.mirai.indidea.controller;

import com.mirai.indidea.dao.UserRepository;
import com.mirai.indidea.entity.User;
import com.mirai.indidea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User user(@PathVariable int id) {
        return userService.find(id);
    }

//    @PostMapping
//    @PutMapping
//    @DeleteMapping
}
