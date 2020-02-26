package com.mirai.indidea.controller;

import com.mirai.indidea.dto.Result.ResultDto;
import com.mirai.indidea.service.ProjectService;
import com.mirai.indidea.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/{id}")
    public ResultDto<Object> findByUserId(@PathVariable int id) {
        return ResultUtils.Result(200, "success", projectService.findByUserId(id));
    }
}
