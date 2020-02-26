package com.mirai.indidea.controller;

import com.mirai.indidea.dto.Result.ResultDto;
import com.mirai.indidea.entity.Category;
import com.mirai.indidea.service.CategoryService;
import com.mirai.indidea.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService service;

    @GetMapping()
    public ResultDto<Object> findAll() {
        List<Category> categories = service.findAll();
        return ResultUtils.Result(200,"success",categories);
    }

    @GetMapping("/{id}")
    public ResultDto<Object> findOne(@PathVariable int id) {
        Category category = service.findOne(id);
        return ResultUtils.Result(200, "success", category);
    }
}
