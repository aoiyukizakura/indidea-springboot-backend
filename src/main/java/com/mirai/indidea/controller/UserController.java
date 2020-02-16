package com.mirai.indidea.controller;

import com.mirai.indidea.dto.Result.ResultDto;
import com.mirai.indidea.dto.Userdto.LoginDto;
import com.mirai.indidea.dto.Userdto.UserRegisterDto;
import com.mirai.indidea.dto.Userdto.UserUpdateDto;
import com.mirai.indidea.entity.User;
import com.mirai.indidea.service.UploadService;
import com.mirai.indidea.service.UserService;
import com.mirai.indidea.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UploadService uploadService;


    /**
     * 查询单个用户
     * @param id 用户id
     * @return User
     */
    @GetMapping("/{id}")
    public ResultDto<Object> user(@PathVariable int id) {
        User u = userService.find(id);
        if (u!=null){
            return ResultUtil.Result(200,"success",u);
        } else {
            return ResultUtil.Result(200, "failure", null);
        }
    }

    /**
     * 用户登录
     * @param loginDto 登录数据
     * @return User
     */
    @PostMapping("/login")
    public ResultDto<Object> login(@RequestBody LoginDto loginDto) {
        User u = userService.login(loginDto);
        if( u != null ){
            return ResultUtil.Result(200,"登录成功", u);
        } else {
            return ResultUtil.Result(200,"登陆失败，请检查邮箱和密码是否正确", false);
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
            return ResultUtil.Result(200, "注册成功", true);
        } else {
            return ResultUtil.Result(200, "注册失败，邮箱已注册",false);
        }
    }

    /**
     * 用户更新
     * @param userUpdateDto 用户更新数据
     * @return ResultDto<Object>
     */
    @PutMapping("/edit")
    public ResultDto<Object> edit(@Valid @RequestBody UserUpdateDto userUpdateDto) {
        if( userService.update(userUpdateDto) ) {
            return ResultUtil.Result(200, "更新成功", true);
        } else {
            return ResultUtil.Result(200, "修改失败", false);
        }
    }

    @PutMapping("/changePassword")
    public boolean changePassword(@RequestParam("id") int id, @RequestParam("password") String password) {
        return userService.changePassword(id, password);
    }

    @PostMapping("/avatar")
    public ResultDto<Object> uploadAvatar(@RequestParam("avatar") MultipartFile file) {
        String fileName = uploadService.Upload(file);
        if (fileName == null) {
            return ResultUtil.Result(200, "上传失败", null);
        } else {
            return ResultUtil.Result(200, "上传成功", fileName);
        }
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
}
