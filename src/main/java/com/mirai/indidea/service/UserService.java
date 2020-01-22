package com.mirai.indidea.service;

import com.mirai.indidea.dao.UserRepository;
import com.mirai.indidea.entity.User;
import com.mirai.indidea.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User find(int id) {
        return userRepository.getOne(id);
    }

    /**
     * save -> 如果主键存在，则会根据主键来更新已有的信息, 不存在，则新增一条
     * @param user 用户
     * @return User
     */
    @Override
    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public boolean delete(int id) {
        User user = new User();
        user.setId(id);
        user.setStatus(0);
        userRepository.saveAndFlush(user);
        return true;
    }
}
