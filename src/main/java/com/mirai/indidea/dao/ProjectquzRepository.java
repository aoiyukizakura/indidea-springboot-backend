package com.mirai.indidea.dao;

import com.mirai.indidea.entity.Projectquz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectquzRepository extends JpaRepository<Projectquz, Integer> {
    List<Projectquz> findByStatusAndProjectId(int status, int project_id);
    Projectquz findById(int id);
}
