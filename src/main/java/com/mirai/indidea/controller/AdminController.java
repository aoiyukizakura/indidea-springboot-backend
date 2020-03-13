package com.mirai.indidea.controller;

import com.mirai.indidea.annotation.AdminToken;
import com.mirai.indidea.dto.Admindto.LoginDto;
import com.mirai.indidea.dto.Result.ResultDto;
import com.mirai.indidea.entity.*;
import com.mirai.indidea.service.AdminService;
import com.mirai.indidea.service.ProjectService;
import com.mirai.indidea.utils.JwtUtils;
import com.mirai.indidea.utils.MD5Utils;
import com.mirai.indidea.utils.PageUtils;
import com.mirai.indidea.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

//    @RequestMapping()
//    public String login(Model model) {
//        return "index";
//    }
//
//    @RequestMapping("/index")
//    public String index(Model m) {
//        Map<String, String> map = new HashMap<>();
//        map.put("msg", "hello world");
//        m.addAttribute("msg", map);
//        return "tables";
//    }
//
//    @RequestMapping("/userList")
//    public String userList(Model model) {
//        List<User> userList = adminService.allUser();
//        model.addAttribute("userList", userList);
//        return "user/userList";
//    }
//
//    @RequestMapping("/doUserApply")
//    public String doUserApply(Model model) {
//        return "user/doUserApply";
//    }
//
//    @RequestMapping("/projectList")
//    public String projectList(Model model) {
//        return "project/projectList";
//    }
//
//    @RequestMapping("/doProject")
//    public String doProject(Model model) {
//        return "project/doProject";
//    }
//
//    @RequestMapping("/reportList")
//    public String reportList(Model model) {
//        return "project/reportList";
//    }
//
//
//    @RequestMapping("/doPost")
//    public String doPost(Model model) {
//        return "post/doPost";
//    }
//
//    @RequestMapping("/postList")
//    public String postList(Model model) {
//        return "post/postList";
//    }
//
//    @RequestMapping("/category")
//    public String category(Model model) {
//        return "category/category";
//    }

    @PostMapping("/login")
    public ResultDto<Object> login(@RequestBody @Valid LoginDto loginDto) {
        Admin a = adminService.login(loginDto.getAdminname(), MD5Utils.crypt(loginDto.getPassword()));
        String token = JwtUtils.adminToken(a);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("adminInfo", a);
        return ResultUtils.success(map);
    }

    @AdminToken
    @GetMapping()
    public boolean test() {
        return true;
    }

    @AdminToken
    @GetMapping("/reportList")
    public ResultDto<Object> reportList(@RequestParam("pageIndex") int pageNum, @RequestParam("pageSize") int pageSize,
                                        @RequestParam("status") Integer status) {
        List<Report> reports = adminService.allReport(status);
        return ResultUtils.pager(reports, pageNum, pageSize);
    }
    @AdminToken
    @PutMapping("/doReport")
    public ResultDto<Object> doReport(@RequestParam("reportId") int reportId,
                                      @RequestParam("status") int status,
                                      HttpServletRequest request) {
        int adminId = JwtUtils.getAdminIdInRequest(request);
        return ResultUtils.success(adminService.doReport(reportId, status, adminService.find(adminId).getAdminname()));
    }

    @AdminToken
    @GetMapping("/projectList")
    public ResultDto<Object> allProject(@RequestParam("status") int status, @RequestParam("title") String title,
                                        @RequestParam("pageIndex") int pageNum,
                                        @RequestParam("pageSize") int pageSize,
                                        @RequestParam(value = "categoryId", required = false) Integer categoryId) {
        List<Project> list = adminService.allProject(status, title, categoryId);
        return ResultUtils.pager(list, pageNum, pageSize);
    }
    @AdminToken
    @PutMapping("/doProject")
    public ResultDto<Object> doProject(@RequestParam("projectId") int projectId,
                                       @RequestParam("status") int status) {
        return ResultUtils.success(adminService.doProject(projectId, status));
    }

    /** 帖子 **/
    @AdminToken
    @GetMapping("/postList")
    public ResultDto<Object> postList(@RequestParam("status") int status,
                                      @RequestParam("pageIndex") int pageNum,
                                      @RequestParam("pageSize") int pageSize) {
        List<Post> posts = adminService.allPost(status);
        return ResultUtils.pager(posts, pageNum, pageSize);
    }
    @AdminToken
    @PutMapping("/doPost")
    public ResultDto<Object> doPost(@RequestParam("postId") int postId,
                                    @RequestParam("status") int status) {
        return ResultUtils.success(adminService.doPost(postId, status));
    }
    @AdminToken
    @GetMapping("/postComment")
    public ResultDto<Object> postComment(@RequestParam("postId") int postId) {
        return ResultUtils.success(adminService.allCommentByPostId(postId));
    }
    @AdminToken
    @PutMapping("/doComment")
    public ResultDto<Object> doComment(@RequestParam("commentId") int commentId,
                                      @RequestParam("status") int status) {
        return ResultUtils.success(adminService.doPostComment(commentId, status));
    }

    /** 分类 **/
    @AdminToken
    @GetMapping("/categoriesList")
    public ResultDto<Object> categoriesList() {
        return ResultUtils.success(adminService.allCategories());
    }
    @AdminToken
    @PutMapping("/doCategories")
    public ResultDto<Object> doCategories(@RequestParam("name") String name) {
        return ResultUtils.success(adminService.doCategories(name));
    }


    /** 用户 **/
    @AdminToken
    @GetMapping("/userList")
    public ResultDto<Object> userList(@RequestParam("status") int status,
                                      @RequestParam("username") String username,
                                      @RequestParam("pageIndex") int pageNum,
                                      @RequestParam("pageSize") int pageSize) {
        List<User> userList;
        if (status != 3)
            userList = adminService.allUser(status,username);
        else
            userList = adminService.allUser(username);
        return ResultUtils.pager(userList, pageNum, pageSize);
    }
    @AdminToken
    @PutMapping("/doUserOrAuth")
    public ResultDto<Object> doUser(@RequestParam("userId") int userId,
                                    @RequestParam("status") int status) {
        Integer[] integers = new Integer[]{0,1};
        if (Arrays.asList(integers).contains(status)) {
            return ResultUtils.success(adminService.doUser(userId, status));
        }
        else
            return ResultUtils.fail();
    }

    @AdminToken
    @GetMapping("/userApplyList")
    public ResultDto<Object> applyList(@RequestParam("status") int status,
                                       @RequestParam("admin") String admin,
                                       @RequestParam("pageIndex") int pageNum,
                                       @RequestParam("pageSize") int pageSize) {
        List<Apply> applyList;
        if(status != 3)
            applyList = adminService.allApply(status, admin);
        else
            applyList = adminService.allApply();
        return ResultUtils.pager(applyList, pageNum, pageSize);
    }

    @AdminToken
    @PutMapping("/doApply")
    public ResultDto<Object> doApply(@RequestParam("status") int status,
                                     @RequestParam("applyId") int id,
                                     HttpServletRequest request) {
        int adminId = JwtUtils.getAdminIdInRequest(request);
        String admin = adminService.find(adminId).getAdminname();
        if (status == 0 || status == 2) {
            return ResultUtils.success(adminService.doUserAuth(id, admin, status));
        } else return ResultUtils.fail();

    }
}