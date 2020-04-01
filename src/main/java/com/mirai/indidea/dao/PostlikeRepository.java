package com.mirai.indidea.dao;

import com.mirai.indidea.entity.Postlike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostlikeRepository extends JpaRepository<Postlike, Integer> {
    long countByPostId(int post_id);
    List<Postlike> findByUserId(int user_id);

    Postlike findByUserIdAndPostId(int uid, int postId);
}
