package com.mirai.indidea.controller;

import com.mirai.indidea.annotation.UserLoginToken;
import com.mirai.indidea.dto.ProjectDto.UpdateProjectDto;
import com.mirai.indidea.dto.Result.ResultDto;
import com.mirai.indidea.entity.Project;
import com.mirai.indidea.service.CategoryService;
import com.mirai.indidea.service.ProjectService;
import com.mirai.indidea.service.UserService;
import com.mirai.indidea.utils.JwtUtils;
import com.mirai.indidea.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/{id}")
    public ResultDto<Object> findByUserId(@PathVariable int id) {
        return ResultUtils.Result(200, "success", projectService.findByUserId(id));
    }

    @GetMapping()
    public ResultDto<Object> findAll() {
        return ResultUtils.Result(200,"success",projectService.findAll(2));
    }

    @UserLoginToken
    @PostMapping()
    public ResultDto<Object> create( @RequestParam("categoryId") int categoryId, HttpServletRequest request ) {
        System.out.println(request);
        int userId = JwtUtils.getIdInRequest(request);
        Project p = projectService.created(userId, categoryId);
        return ResultUtils.Result(200, "success", p);
    }

}
