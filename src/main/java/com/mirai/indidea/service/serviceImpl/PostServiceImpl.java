package com.mirai.indidea.service.serviceImpl;

import com.mirai.indidea.dao.PostCommentRepository;
import com.mirai.indidea.dao.PostRepository;
import com.mirai.indidea.dao.PostlikeRepository;
import com.mirai.indidea.entity.Post;
import com.mirai.indidea.entity.Postcomment;
import com.mirai.indidea.entity.Postlike;
import com.mirai.indidea.entity.User;
import com.mirai.indidea.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostCommentRepository commentRepository;

    @Autowired
    private PostlikeRepository postlikeRepository;

    @Override
    public Post findOne(int id) {
        return postRepository.getOne(id);
    }

    @Override
    public List<Post> allPost(Integer status, String content) {
        if(content.equals(""))
            return postRepository.findPostsByStatus(status);
        else return postRepository.findPostsByStatusAndContent(status, content);
    }

    @Override
    public boolean sendPost(String content, String images, Integer userId) {
        try{
            Post post = new Post();
            post.setContent(content);
            User user = new User();
            user.setId(userId);
            post.setUser(user);
            post.setCover(images);
            postRepository.saveAndFlush(post);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Map<String, Object>> postList() {
        return postRepository.findByStatus();
    }

    @Override
    public long likeNum(int postId) {
        return postlikeRepository.countByPostId(postId);
    }

    @Override
    public long commentNum(int postId) {
        return commentRepository.countByPostId(postId);
    }

    @Override
    public List<Postcomment> commentByPostId(int postId) {
        return commentRepository.findPostcommentsByPostId(postId);
    }

    @Override
    public boolean addComment(int postId, String content, int idInRequest) {
        try {
            User user = new User();
            user.setId(idInRequest);
            Post post = new Post();
            post.setId(postId);
            Postcomment postcomment = new Postcomment();
            postcomment.setComment(content);
            postcomment.setUser(user);
            postcomment.setPost(post);
            commentRepository.saveAndFlush(postcomment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deletePost(Post post) {
        try {
            postRepository.delete(post);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean doLike(Postlike postlike) {
        try {
            postlikeRepository.saveAndFlush(postlike);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String checkLike(int postId, int uid) {
        Postlike postlike = postlikeRepository.findByUserIdAndPostId(uid, postId);
        if (postlike != null) {
            return "like";
        } else {
            return "unlike";
        }
    }

    @Override
    public boolean unLike(int postId, int uid) {
        try {
            Postlike postlike = postlikeRepository.findByUserIdAndPostId(uid, postId);
            postlikeRepository.delete(postlike);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
