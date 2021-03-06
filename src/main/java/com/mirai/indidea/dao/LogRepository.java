package com.mirai.indidea.dao;

import com.mirai.indidea.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Integer> {
    List<Log> findByProjectIdAndStatusOrderByNumberDesc(int project_id, int status);

    int countByProjectId(int project_id);
}
