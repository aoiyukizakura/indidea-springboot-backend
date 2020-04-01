package com.mirai.indidea.controller;

import com.mirai.indidea.dao.PostCommentRepository;
import com.mirai.indidea.dto.Result.ResultDto;
import com.mirai.indidea.entity.Post;
import com.mirai.indidea.entity.Postlike;
import com.mirai.indidea.entity.User;
import com.mirai.indidea.service.PostService;
import com.mirai.indidea.utils.JwtUtils;
import com.mirai.indidea.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping()
    public ResultDto<Object> postList() {
        return ResultUtils.success(postService.postList());
    }

    @GetMapping("/commentByPostId/{postId}")
    public ResultDto<Object> comment(@PathVariable int postId) {
        return ResultUtils.success(postService.commentByPostId(postId));
    }

    @PostMapping("/doPost")
    public ResultDto<Object> doPost(@RequestParam("content") String content,
                                    @RequestParam("images") String images,
                                    HttpServletRequest request) {
        return ResultUtils.success(postService.sendPost(content, images, JwtUtils.getIdInRequest(request)));
    }

    @PostMapping("/doComment/{postId}")
    public ResultDto<Object> doComment(@PathVariable int postId,
                                       @RequestParam("content") String content,
                                       HttpServletRequest request) {
        if(content.equals("")) {
            return ResultUtils.fail();
        } else {
            if(postService.findOne(postId) == null) {
                return ResultUtils.fail();
            } else {
                return ResultUtils.success(postService.addComment(postId, content, JwtUtils.getIdInRequest(request)));
            }
        }
    }

    @PostMapping("/like/{postId}")
    public ResultDto<Object> doLike(@PathVariable int postId,
                                    HttpServletRequest request) {
        int uid = JwtUtils.getIdInRequest(request);
        String status = postService.checkLike(postId, uid);
        Postlike postlike = new Postlike();
        if (status.equals("like")) {
            return ResultUtils.success(postService.unLike(postId, uid));
        } else if (status.equals("unlike")) {
            Post post = new Post();
            post.setId(postId);
            User user = new User();
            user.setId(uid);
            postlike.setPost(post);
            postlike.setUser(user);
            return ResultUtils.success(postService.doLike(postlike));
        } else {
            return ResultUtils.fail();
        }
    }

    @DeleteMapping("/{postId}")
    public ResultDto<Object> deletePost(@PathVariable int postId,
                                        HttpServletRequest request) {
        Post post = postService.findOne(postId);
        if (post.getUser().getId() != JwtUtils.getIdInRequest(request)) {
            return ResultUtils.fail();
        } else {
            return ResultUtils.success(postService.deletePost(post));
        }
    }

}
