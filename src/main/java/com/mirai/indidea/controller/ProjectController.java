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
import javax.validation.Valid;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/{id}")
    public ResultDto<Object> findById(@PathVariable int id) {
        return ResultUtils.Result(200, "success", projectService.findProject(id));
    }

    @GetMapping()
    public ResultDto<Object> findAll() {
        return ResultUtils.success(projectService.test());
    }

    @UserLoginToken
    @PostMapping()
    public ResultDto<Object> create( @RequestBody UpdateProjectDto projectDto,
                                     HttpServletRequest request ) {
        int userId = JwtUtils.getIdInRequest(request);
        Project p = projectService.create(userId, projectDto.getCategoryId());
        return ResultUtils.Result(200, "success", p);
    }

    @UserLoginToken
    @PostMapping("/update")
    public ResultDto<Object> update(@Valid @RequestBody UpdateProjectDto updateProjectDto) {
        return ResultUtils.success(updateProjectDto);
    }

    @GetMapping("/top9Project")
    public ResultDto<Object> top9Project() {
        return ResultUtils.success(projectService.top9Project());
    }

    @GetMapping("/featuredProject")
    public ResultDto<Object> featured() {
        return ResultUtils.success(projectService.FeaturedProject());
    }

    @GetMapping("/topHitProject")
    public ResultDto<Object> topHit() {
        return ResultUtils.success(projectService.topHitProject());
    }
}
