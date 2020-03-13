package com.mirai.indidea.dao;

import com.mirai.indidea.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findPostsByStatusAndContent(Integer status, String content);
    List<Post> findPostsByStatus(Integer status);
    List<Post> findPostsByStatusNotIn(Collection<Integer> status);


//    @Query(nativeQuery = true, value = "")
//    List<Map<String,Object>> test();
}
