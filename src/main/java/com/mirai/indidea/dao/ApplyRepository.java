package com.mirai.indidea.dao;

import com.mirai.indidea.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyRepository extends JpaRepository<Apply, Integer> {
    List<Apply> findByStatusAndEditbyLike(int status, String editby);
    List<Apply> findByStatus(int status);
    Apply findApplyById(int id);
    long countByStatusAndUserId(int status, int user_id);
}
