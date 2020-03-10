package com.mirai.indidea.dao;

import com.mirai.indidea.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(int id);
    User findUserByEmailAndPasswordAndStatusNot(String email, String password, int status);
    User findUserByEmail(String email);
    List<User> findByStatusAndUsernameLike(int status, String username);
    List<User> findByUsernameLike(String username);
//    public User
}
