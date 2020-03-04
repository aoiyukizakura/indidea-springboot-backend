package com.mirai.indidea.service;

import com.mirai.indidea.dto.ProjectDto.UpdateProjectDto;
import com.mirai.indidea.entity.Project;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    Project findProject(Integer id);
    Object findByUserId(Integer id);
    List<Project> findAll(Integer id);
    Project create(Integer userId, UpdateProjectDto projectDto);
    Project update(UpdateProjectDto projectDto);
    List<Project> top9Project();
    List<Map<String,Object>> test();
    Project FeaturedProject();
    List<Project> topHitProject();
    Project getProjectDetail(int projectId);
    Project getEditProject(int projectId, int ownerId);
    Project waitCheckProject(int projectId);

    Project backToEdit(Integer projectId);

    Project sendProject(Integer projectId);
}
