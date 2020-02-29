package com.mirai.indidea.dao;

import com.mirai.indidea.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Project findProjectByCategoryAndId(Integer category, Integer id);
    List<Project> findAll();
    List<Project> findProjectByCategoryId(Integer id);
    Project findProjectById(Integer id);
    List<Project> findProjectByOwnerId(Integer id);
    Project findProjectByStatus(Integer status);
    List<Project> findTop9ByStatusOrderByUpdatedatDesc(Integer status);

    @Query(nativeQuery = true, value = "SELECT owner_id,sum(targetpoint) FROM indidea.project group by owner_id;")
    List<Map<String,Object>> test();
}
