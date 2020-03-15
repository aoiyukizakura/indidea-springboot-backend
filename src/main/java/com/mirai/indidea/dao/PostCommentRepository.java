package com.mirai.indidea.dao;

import com.mirai.indidea.entity.Postcomment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface PostCommentRepository extends JpaRepository<Postcomment, Integer> {

    List<Postcomment> findPostcommentsByStatusAndPostId(Integer status, Integer post_id);

    List<Postcomment> findPostcommentsByPostId(Integer post_id);

    Postcomment findPostcommentById(Integer id);

    Integer countByPostId(Integer post_id);

//    @Query(nativeQuery = true, value = "SELECT (id, )")
//    List<Map<String, Object>> findPostcommentByPostId(Integer id);

}