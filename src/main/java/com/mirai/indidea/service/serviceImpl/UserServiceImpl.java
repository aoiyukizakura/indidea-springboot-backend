package com.mirai.indidea.service.serviceImpl;

import com.mirai.indidea.dao.UserRepository;
import com.mirai.indidea.dto.Userdto.LoginDto;
import com.mirai.indidea.dto.Userdto.UserRegisterDto;
import com.mirai.indidea.entity.User;
import com.mirai.indidea.service.UserService;
import com.mirai.indidea.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;


@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User find(int id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User login(LoginDto logindto) {
        return userRepository
                .findUserByEmailAndPassword(
                logindto.getEmail(),
                MD5Util.crypt(logindto.getPassword())
        );
    }

    /**
     * save -> 如果主键存在，则会根据主键来更新已有的信息, 不存在，则新增一条
     * @param userRegisterDto 用户
     * @return User
     */
    @Override
    public boolean save(UserRegisterDto userRegisterDto) {
        if (userRepository.findUserByEmail(userRegisterDto.getEmail()) == null) {
            User user = new User();
            user.setUsername(userRegisterDto.getUsername());
            user.setPassword(MD5Util.crypt(userRegisterDto.getPassword()));
            user.setEmail(userRegisterDto.getEmail());
            userRepository.saveAndFlush(user);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean delete(int id) {
        User user = userRepository.findUserById(id);
        user.setStatus(0);
        userRepository.saveAndFlush(user);
        return true;
    }

}
