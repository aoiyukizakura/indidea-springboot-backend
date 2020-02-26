package com.mirai.indidea.service;

import com.mirai.indidea.entity.Project;

import java.util.List;

public interface ProjectService {
    Project findProject(Integer cid, Integer id);
    Object findByUserId(Integer id);
    List<Project> findAll(Integer id);
    Project created(Integer userId, Integer categoryId);
}
