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

    @Query(value = "SELECT coalesce(sum(p.id=pc.post_id), 0) as comment_sum, coalesce(sum(p.id = pl.id), 0) as like_sum, p.id, p.user_id, p.content, p.createdat, p.updatedat, p.status, p.cover,u.username,u.avatar " +
            "FROM indidea.post p left join postcomment pc on p.id = pc.post_id left join postlike pl on p.id = pl.id join user u on u.id=p.user_id where p.status = 1 group by p.id;", nativeQuery = true)
    List<Map<String, Object>> findByStatus();
//    @Query(nativeQuery = true, value = "")
//    List<Map<String,Object>> test();
}
