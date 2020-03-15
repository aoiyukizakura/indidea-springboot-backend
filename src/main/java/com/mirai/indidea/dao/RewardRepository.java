package com.mirai.indidea.dao;

import com.mirai.indidea.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RewardRepository extends JpaRepository<Reward, Integer> {
    List<Reward> findByProjectId(int project_id);
    Reward findById(int id);
}
