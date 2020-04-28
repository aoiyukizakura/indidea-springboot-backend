package com.mirai.indidea.dao;

import com.mirai.indidea.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findPostsByStatusAndContent(Integer status, String content);
    List<Post> findPostsByStatus(Integer status);
    List<Post> findPostsByStatusNotIn(Collection<Integer> status);

    @Query(value = "SELECT p.id, p.user_id, p.content, p.createdat, p.updatedat, p.status, p.cover, u.username, u.avatar, pro.id as project_id, pro.title as project_title, " +
            "(select count(*) from postlike pl where p.id = pl.post_id) as like_count, " +
            "(select count(*) from postcomment pc where p.id = pc.post_id ) as comment_count, " +
            "(select count(*) from postlike pl where p.id = pl.post_id and pl.user_id = :user_id) as like_check " +
            "FROM indidea.post p left join user u on u.id = p.user_id " +
            "left join project pro on pro.id = p.project_id " +
            "where p.status = 1 order by p.createdat desc limit :limit offset :offset", nativeQuery = true)
    List<Map<String, Object>> findByStatus(int user_id, int limit, int offset);

    @Query(nativeQuery = true, value = "select * from (SELECT p.id, p.user_id, p.content, p.createdat, p.updatedat, p.status, p.cover, u.username, u.avatar, pro.id as project_id, pro.title as project_title, " +
            "(select count(*) from postlike pl where p.id = pl.post_id) like_count, " +
            "(select count(*) from postcomment pc where p.id = pc.post_id ) comment_count, " +
            "(select count(*) from postlike pl where p.id = pl.post_id and pl.user_id = :user_id) like_check " +
            "FROM indidea.post p left join user u on u.id = p.user_id " +
            "left join project pro on pro.id = p.project_id " +
            "where p.status = 1) a where like_check = 1 order by a.createdat desc limit :limit offset :offset")
    List<Map<String, Object>> findUserLikeList(int user_id, int limit, int offset);

    @Query(value = "SELECT p.id, p.user_id, p.content, p.createdat, p.updatedat, p.status, p.cover, u.username, u.avatar, pro.id as project_id, pro.title as project_title, " +
            "(select count(*) from postlike pl where p.id = pl.post_id) as like_count, " +
            "(select count(*) from postcomment pc where p.id = pc.post_id ) as comment_count, " +
            "(select count(*) from postlike pl where p.id = pl.post_id and pl.user_id = :userId) as like_check " +
            "FROM indidea.post p left join user u on u.id = p.user_id " +
            "left join project pro on pro.id = p.project_id " +
            "where p.status = 1 and p.user_id = :userId order by p.createdat desc limit :limit offset :offset", nativeQuery = true)
    List<Map<String, Object>> findUserList(int userId, int limit, int offset);

    @Query(value = "SELECT p.id, p.user_id, p.content, p.createdat, p.updatedat, p.status, p.cover, u.username, u.avatar, pro.id as project_id, pro.title as project_title, " +
            "(select count(*) from postlike pl where p.id = pl.post_id) as like_count, " +
            "(select count(*) from postcomment pc where p.id = pc.post_id ) as comment_count, " +
            "(select count(*) from postlike pl where p.id = pl.post_id and pl.user_id = :user_id) as like_check " +
            "FROM indidea.post p left join user u on u.id = p.user_id " +
            "left join project pro on pro.id = p.project_id " +
            "where p.status = 1 and project_id = :projectId order by p.createdat desc limit :limit offset :offset", nativeQuery = true)
    List<Map<String, Object>> findProjectCommunityList(int projectId, int user_id, int limit, int offset);
}
