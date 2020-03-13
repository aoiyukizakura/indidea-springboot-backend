package com.mirai.indidea.service;

import com.mirai.indidea.entity.Post;

import java.util.List;

public interface PostService {
    List<Post> allPost(Integer status, String content);
    boolean sendPost(String content, Integer userId);
}
