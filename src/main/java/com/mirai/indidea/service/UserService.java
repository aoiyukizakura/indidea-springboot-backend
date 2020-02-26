package com.mirai.indidea.service;

import com.mirai.indidea.dto.Userdto.LoginDto;
import com.mirai.indidea.dto.Userdto.UserRegisterDto;
import com.mirai.indidea.dto.Userdto.UserUpdateDto;
import com.mirai.indidea.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

public interface UserService {

    User find(int id);
    User login(LoginDto logindto);
    boolean save(@Valid UserRegisterDto userRegisterDto);
    boolean delete(int id);
    boolean update(@Valid UserUpdateDto userUpdateDto, int id);
    boolean changePassword(int id, String password);
    boolean logout(HttpServletRequest request);
}

