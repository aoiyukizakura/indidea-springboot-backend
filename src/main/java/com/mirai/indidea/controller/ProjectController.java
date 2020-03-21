package com.mirai.indidea.controller;

import com.auth0.jwt.JWT;
import com.mirai.indidea.annotation.UserLoginToken;
import com.mirai.indidea.dto.ProjectDto.QueryDto;
import com.mirai.indidea.dto.ProjectDto.UpdateProjectDto;
import com.mirai.indidea.dto.Result.ResultDto;
import com.mirai.indidea.entity.Project;
import com.mirai.indidea.service.CategoryService;
import com.mirai.indidea.service.ProjectService;
import com.mirai.indidea.service.UserService;
import com.mirai.indidea.utils.JwtUtils;
import com.mirai.indidea.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    //TODO 不能查询被禁用用户的项目
    @GetMapping("/{id}")
    public ResultDto<Object> findById(@PathVariable int id) {
        return ResultUtils.Result(200, "success", projectService.findProject(id));
    }

    @GetMapping()
    public ResultDto<Object> findAll(@RequestParam("id") Integer id) {
        return ResultUtils.success(projectService.findById(id));
    }

    @UserLoginToken
    @PostMapping()
    public ResultDto<Object> create( @RequestBody UpdateProjectDto projectDto,
                                     HttpServletRequest request ) {
//        return ResultUtils.success(projectDto);
        int userId = JwtUtils.getIdInRequest(request);
        Project p = projectService.create(userId, projectDto);
        return ResultUtils.Result(200, "success", p);
    }

    @UserLoginToken
    @PostMapping("/update")
    public ResultDto<Object> update(@Valid @RequestBody UpdateProjectDto updateProjectDto) {
        return ResultUtils.success(projectService.update(updateProjectDto));
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

    /**
     * @param query id 项目id flag 0编辑状态 1正常状态
     * @param request re
     * @return ResultDto
     */
    @UserLoginToken
    @GetMapping("/getProjectByFlagById")
    public ResultDto<Object> getProjectByStatus(@Valid QueryDto query, HttpServletRequest request) {
//        System.out.println(query.getProjectId());
        if(query.getFlag() == 0) {
            return ResultUtils.success(projectService.getEditProject(query.getProjectId(), JwtUtils.getIdInRequest(request)));
        } else {
            return  ResultUtils.success(projectService.getProjectDetail(query.getProjectId()));
        }
    }

    @UserLoginToken
    @PutMapping("/waitCheckProject")
    public ResultDto<Object> waitCheckProject(@Valid @RequestBody QueryDto query) {
//        return ResultUtils.success(query);
        Project p = projectService.waitCheckProject(query.getProjectId());
        return ResultUtils.success(p);
    }

    @UserLoginToken
    @PutMapping("/backToEdit")
    public ResultDto<Object> backToEdit(@Valid @RequestBody QueryDto query) {
        Project p = projectService.backToEdit(query.getProjectId());
        return ResultUtils.success(p);
    }

    @UserLoginToken
    @PutMapping("/sendProject")
    public ResultDto<Object> sendProject(@Valid @RequestBody QueryDto query) {
        Project p = projectService.sendProject(query.getProjectId());
        return ResultUtils.success(p);
    }

    /**
     * search Project
     * @param categoryId 分类
     * @param sort 根据什么排序 1根据最先发布 2根据点击量 3结束日期 4最多筹款
     * @param keyword 关键字
     * @param status  1进行中 2已结束
     * @return ResultDto
     */
    @GetMapping("/searchProject")
    public ResultDto<Object> searchProject(@RequestParam(required = false, value = "categoryId") Integer categoryId,
                                           @RequestParam(required = false, value = "sort", defaultValue = "0") Integer sort,
                                           @RequestParam(required = false, value = "keyword", defaultValue = "") String keyword,
                                           @RequestParam(required = false, value = "status", defaultValue = "1") Integer status,
                                           @RequestParam(required = false, value = "page", defaultValue = "0") int page) {
        int pageSize = 9;
        Pageable the9Element;
        String sortBy;
        switch (sort) {
            case 1:
                sortBy = "createdat";
                break;
            case 2:
                sortBy = "hittime";
                break;
            case 3:
                sortBy = "targetdate";
                break;
            case 4:
                sortBy = "getpoint";
                break;
            default:
                sortBy = "id";
                break;
        }
        the9Element = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());
        if (categoryId == 0) {
            categoryId = null;
        }
        List<Project> lists = projectService.search(keyword, categoryId, the9Element, status);
        Integer total = projectService.count(keyword, categoryId, status);
        Map<String, Object> map = new HashMap<>();
        map.put("list", lists);
        map.put("total", total);
        return ResultUtils.success(map);
    }

    /**
     * project Detail
     */
    @GetMapping("/countSponsorByProjectId/{id}")
    public ResultDto<Object> countSponsorByProjectId(@PathVariable int id) {
        return  ResultUtils.success(projectService.countSponsor(id));
    }

    @UserLoginToken
    @PutMapping("/saveProject")
    public ResultDto<Object> saveProject(@RequestParam("projectId") int projectId,
                                         @RequestParam("flag") int flag,
                                         HttpServletRequest request) {
        int userId = JwtUtils.getIdInRequest(request);
        if (flag == 1)
            return ResultUtils.success(projectService.saveProject(projectId, userId));
        else
            return ResultUtils.success(projectService.deleteSave(projectId,userId));
    }

    @UserLoginToken
    @GetMapping("/saveStatus")
    public ResultDto<Object> saveStatus(@RequestParam("projectId") Integer projectId, HttpServletRequest request) {
        int userId = JwtUtils.getIdInRequest(request);
        return ResultUtils.success(projectService.saveStatus(projectId, userId));
    }

    @UserLoginToken
    @PostMapping("/supportProject")
    public ResultDto<Object> supportProject(@RequestParam("projectId") int projectId,
                                            @RequestParam("point") int point,
                                            @RequestParam( required = false, value = "rewardId", defaultValue = "0") Integer rewardId,
                                            HttpServletRequest request) {
        int userId = JwtUtils.getIdInRequest(request);
        return ResultUtils.success(projectService.supportProject(projectId, userId, point, rewardId));

    }


//    @UserLoginToken
//    @PutMapping("/saveBasic")
//    public ResultDto<Object> saveBasic(@RequestBody Project project) {
//        try {
//            projectService.update(project);
//            return ResultUtils.success(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResultUtils.fail();
//        }
//    }

}
