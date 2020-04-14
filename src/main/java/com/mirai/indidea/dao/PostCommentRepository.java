package com.mirai.indidea.dao;

import com.mirai.indidea.entity.Postcomment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<Postcomment, Integer> {

    List<Postcomment> findPostcommentsByStatusAndPostId(Integer status, Integer post_id);

    List<Postcomment> findPostcommentsByPostIdAndStatus(int post_id, int status);
    List<Postcomment> findPostcommentsByPostId(int post_id);
    Postcomment findPostcommentById(Integer id);

    Integer countByPostId(Integer post_id);

//    @Query(nativeQuery = true, value = "SELECT (id, )")
//    List<Map<String, Object>> findPostcommentByPostId(Integer id);

}
