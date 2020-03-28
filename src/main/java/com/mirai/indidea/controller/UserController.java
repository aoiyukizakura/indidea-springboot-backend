package com.mirai.indidea.controller;

import com.mirai.indidea.annotation.UserLoginToken;
import com.mirai.indidea.dto.Result.ResultDto;
import com.mirai.indidea.dto.Userdto.LoginDto;
import com.mirai.indidea.dto.Userdto.UserRegisterDto;
import com.mirai.indidea.dto.Userdto.UserUpdateDto;
import com.mirai.indidea.entity.Project;
import com.mirai.indidea.entity.Sponsor;
import com.mirai.indidea.entity.User;
import com.mirai.indidea.service.UserService;
import com.mirai.indidea.utils.JwtUtils;
import com.mirai.indidea.utils.ResultUtils;
import com.mirai.indidea.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询单个用户
     * @param id 用户id
     * @return User
     */
    @GetMapping("/{id}")
    public ResultDto<Object> user(@PathVariable int id) {
        User u = userService.find(id);
        if (u!=null){
            u.setPassword(null);
            return ResultUtils.Result(200,"success",u);
        } else {
            return ResultUtils.Result(200, "failure", null);
        }
    }

    @UserLoginToken
    @GetMapping()
    public ResultDto me(HttpServletRequest request) {
        User u = userService.find(JwtUtils.getIdInRequest(request));
        if (u != null){
            return ResultUtils.Result(200,"success",u);
        } else {
            return ResultUtils.Result(200, "failure", null);
        }
    }

    /**
     * 用户登录
     * @param loginDto 登录数据
     * @return User
     */
    @PostMapping("/login")
    public ResultDto<Object> login(@RequestBody @Valid LoginDto loginDto) {
        User u = userService.login(loginDto);
        if( u != null ){
            String token = JwtUtils.getToken(u);
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("userInfo", u);
            return ResultUtils.Result(200,"登录成功", map);
        } else {
            return ResultUtils.Result(200,"登陆失败，请检查邮箱和密码是否正确", false);
        }
    }

    /**
     * 用户注册
     * @param userRegisterDto 用户注册信息
     * @return User
     */
    @PostMapping("/register")
    public ResultDto<Object> register(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        if( userService.save(userRegisterDto) ) {
            return ResultUtils.Result(200, "注册成功", true);
        } else {
            return ResultUtils.Result(200, "注册失败，邮箱已注册",false);
        }
    }

    /**
     * 用户更新
     * @param userUpdateDto 用户更新数据
     * @return ResultDto<Object>
     */
    @UserLoginToken
    @PutMapping()
    public ResultDto<Object> update( @Valid @RequestBody UserUpdateDto userUpdateDto,
                                  HttpServletRequest request ) {
        if( userService.update(userUpdateDto, JwtUtils.getIdInRequest(request)) ) {
            return ResultUtils.Result(200, "更新成功", true);
        } else {
            return ResultUtils.Result(200, "修改失败", false);
        }
    }

    @UserLoginToken
    @PostMapping("/avatar")
    public ResultDto<Object> uploadAvatar( @RequestParam("avatar") MultipartFile file,
            HttpServletRequest request ) {
        String fileName = UploadUtils.Upload(file);
        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setAvatar(fileName);
        userService.update(userUpdateDto, JwtUtils.getIdInRequest(request));
        return ResultUtils.Result(200, "上传成功", fileName);
    }

    @GetMapping("/logout")
    public ResultDto<Object> logout(HttpServletRequest request) {
        return ResultUtils.Result(200, "修改失败", userService.logout(request));
    }

    @UserLoginToken
    @GetMapping("/getMyProjects")
    public ResultDto<Object> getProject(HttpServletRequest request) {
        int userId = JwtUtils.getIdInRequest(request);
        List<Project> projectList = userService.findMyProject(userId);
        return ResultUtils.success(projectList);
    }

    /**
     * 禁用用户
     * @param id 用户id
     * @return boolean
     */
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable int id) {
        return userService.delete(id);
    }

    //TODO d
    @UserLoginToken
    @GetMapping("/pointList")
    public ResultDto<Object> pointList(HttpServletRequest request) {
        int userId = JwtUtils.getIdInRequest(request);
        return ResultUtils.success(userService.pointList(userId));
    }
    //TODO d
    @UserLoginToken
    @GetMapping("/addPoint")
    public ResultDto<Object> addPoint(@RequestParam("point") int point,
                                      HttpServletRequest request) {
        int userId = JwtUtils.getIdInRequest(request);
        return ResultUtils.success(userService.addPoint(userId, point));
    }
    //TODO do
    @UserLoginToken
    @GetMapping("/supportNum")
    public ResultDto<Object> supportNum(HttpServletRequest request) {
        int userId = JwtUtils.getIdInRequest(request);
        return ResultUtils.success(userService.supportNum(userId));
    }
    //TODO do
    @UserLoginToken
    @GetMapping("/myFavProject")
    public ResultDto<Object> myFavProject(HttpServletRequest request) {
        int userId = JwtUtils.getIdInRequest(request);
        return ResultUtils.success(userService.myFavProject(userId));
    }
    //TODO do
    @UserLoginToken
    @GetMapping("/mySupport")
    public ResultDto<Object> mySupport(HttpServletRequest request) {
        int userId = JwtUtils.getIdInRequest(request);
        List<Sponsor> sponsors = userService.muSupport(userId);
        List<Sponsor> newList = new LinkedList<>();
        for (Sponsor s : sponsors) {
            boolean b = newList.stream().anyMatch(s1 -> s1.getProject().getId() == s.getProject().getId());
            if (!b){
                newList.add(s);
            }
        }
        return ResultUtils.success(newList);
    }
}
