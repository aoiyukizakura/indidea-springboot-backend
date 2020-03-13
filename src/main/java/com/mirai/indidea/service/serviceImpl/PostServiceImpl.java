package com.mirai.indidea.service.serviceImpl;

import com.mirai.indidea.dao.PostCommentRepository;
import com.mirai.indidea.dao.PostRepository;
import com.mirai.indidea.entity.Post;
import com.mirai.indidea.entity.User;
import com.mirai.indidea.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostCommentRepository commentRepository;

    @Override
    public List<Post> allPost(Integer status, String content) {
        if(content.equals(""))
            return postRepository.findPostsByStatus(status);
        else return postRepository.findPostsByStatusAndContent(status, content);
    }

    @Override
    public boolean sendPost(String content, Integer userId) {
        try{
            Post post = new Post();
            post.setContent(content);
            User user = new User();
            user.setId(userId);
            post.setUser(user);
            postRepository.saveAndFlush(post);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
