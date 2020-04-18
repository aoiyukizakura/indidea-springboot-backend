package com.mirai.indidea.controller;

import com.mirai.indidea.dto.Result.ResultDto;
import com.mirai.indidea.service.PostService;
import com.mirai.indidea.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    PostService postService;

    @GetMapping("/post")
    public ResultDto<Object> post() {
        return ResultUtils.success(postService.allPost(1,""));
    }

}
