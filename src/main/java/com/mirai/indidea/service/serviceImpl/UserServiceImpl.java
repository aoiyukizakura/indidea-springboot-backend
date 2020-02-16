package com.mirai.indidea.service.serviceImpl;

import com.mirai.indidea.dao.UserRepository;
import com.mirai.indidea.dto.Userdto.LoginDto;
import com.mirai.indidea.dto.Userdto.UserRegisterDto;
import com.mirai.indidea.dto.Userdto.UserUpdateDto;
import com.mirai.indidea.entity.User;
import com.mirai.indidea.service.UserService;
import com.mirai.indidea.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.util.Date;


@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 查找用户详细信息
     * @param id 用户id
     * @return User
     */
    @Override
    public User find(int id) {
        return userRepository.findUserById(id);
    }

    /**
     * 用户登录
     * @param logindto 用户登录信息
     * @return User
     */
    @Override
    public User login(LoginDto logindto) {
        return userRepository
                .findUserByEmailAndPassword(
                logindto.getEmail(),
                MD5Util.crypt(logindto.getPassword())
        );
    }

    /**
     * save 用户注册 -> 先查找是否存在邮件，无则新增，有就报错
     * @param userRegisterDto 用户注册信息
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

    /**
     * 用户更新信息
     * @param userUpdateDto 用户更新信息数据
     * @return boolean
     */
    @Override
    public boolean update(@Valid UserUpdateDto userUpdateDto) {
        User u = userRepository.findUserById(userUpdateDto.getId());
        if (userUpdateDto.getUsername() != null)
            u.setUsername(userUpdateDto.getUsername());
        if (userUpdateDto.getWebsite() != null)
            u.setWebsite(userUpdateDto.getWebsite());
        if (userUpdateDto.getAddress() != null)
            u.setAddress(userUpdateDto.getAddress());
        if (userUpdateDto.getDes() != null)
            u.setDes(userUpdateDto.getDes());
        userRepository.saveAndFlush(u);
        return true;
    }

    /**
     * 更新密码
     * */
    @Override
    public boolean changePassword(int id, String password) {
        User u = userRepository.findUserById(id);
        u.setPassword(MD5Util.crypt(password));
        userRepository.saveAndFlush(u);
        return true;
    }

    /**
     * 禁用用户
     * @param id 用户id
     * @return boolean
     */
    @Override
    public boolean delete(int id) {
        User user = userRepository.findUserById(id);
        user.setStatus(0);
        userRepository.saveAndFlush(user);
        return true;
    }

}
