package com.mirai.indidea.service;

import com.mirai.indidea.entity.Post;
import com.mirai.indidea.entity.Postcomment;
import com.mirai.indidea.entity.Postlike;

import java.util.List;
import java.util.Map;

public interface PostService {
    List<Post> allPost(Integer status, String content);
    Post findOne(int id);
    boolean sendPost(String content, String images, Integer userId);

    List<Map<String, Object>> postList();
    long likeNum(int postId);
    long commentNum(int postId);

    List<Postcomment> commentByPostId(int postId);

    boolean addComment(int postId, String content, int idInRequest);

    boolean deletePost(Post post);

    boolean doLike(Postlike postlike);

    String checkLike(int postId, int uid);

    boolean unLike(int postId, int uid);
}
