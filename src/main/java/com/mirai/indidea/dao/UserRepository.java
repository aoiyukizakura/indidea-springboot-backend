package com.mirai.indidea.dao;

import com.mirai.indidea.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(int id);
    User findUserByEmailAndPassword(String email, String password);
    User findUserByEmail(String email);

//    public User
}
