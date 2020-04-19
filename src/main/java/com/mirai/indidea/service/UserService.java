package com.mirai.indidea.service;

import com.mirai.indidea.dto.Userdto.LoginDto;
import com.mirai.indidea.dto.Userdto.UserRegisterDto;
import com.mirai.indidea.dto.Userdto.UserUpdateDto;
import com.mirai.indidea.entity.*;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

public interface UserService {

    User find(int id);
    User login(LoginDto logindto);
    boolean save(@Valid UserRegisterDto userRegisterDto);
    boolean delete(int id);
    boolean update(@Valid UserUpdateDto userUpdateDto, int id);
    boolean changePassword(int id, String password);
    boolean logout(HttpServletRequest request);
    List<Project> findMyProject(Integer id);

    List<Point> pointList(int userId);
    List<Point> pointList(int idInRequest, Pageable pageable);

    boolean addPoint(int userId, int point);

    long supportNum(int userId);

    List<Favorite> myFavProject(int userId, Pageable pageable);
    long myFavProject(int userId);

    List<Sponsor> muSupport(int userId);

    List<Sponsor> supportHistory(int projectId, int idInRequest);

    boolean getApplyStatus(int idInRequest);

    boolean doApply(Integer idInRequest);
}

