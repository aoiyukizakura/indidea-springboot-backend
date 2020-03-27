package com.mirai.indidea.dao;

import com.mirai.indidea.entity.Msgboard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MsgboardRepository extends JpaRepository<Msgboard, Integer> {
    List<Msgboard> findByProjectIdAndStatusOrderByCreatedatDesc(int project_id, int status);


}
