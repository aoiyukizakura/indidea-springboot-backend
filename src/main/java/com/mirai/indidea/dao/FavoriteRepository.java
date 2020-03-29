package com.mirai.indidea.dao;

import com.mirai.indidea.entity.Favorite;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    Favorite findByProjectIdAndUserId(int project_id, int user_id);
    void deleteByProjectIdAndUserId(int project_id, int user_id);
    List<Favorite> findByUserId(int user_id, Pageable pageable);
    long countByUserId(int user_id);
    long countByProjectId(int project_id);
}
