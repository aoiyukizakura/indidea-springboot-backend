package com.mirai.indidea.dao;

import com.mirai.indidea.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Project findProjectByCategoryAndId(Integer category, Integer id);
}
