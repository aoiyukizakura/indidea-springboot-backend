package com.mirai.indidea.controller;

import com.mirai.indidea.annotation.UserLoginToken;
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

    /**
     * 获取post列表
     * @param status 获取列表状态，0 全部列表， 1 我喜欢的列表, 2 我发表的
     * @param request 请求头
     * @return ResultDto
     */
    @GetMapping("/{status}")
    public ResultDto<Object> postList(@PathVariable int status,
                                      @RequestParam("page") int page,
                                      @RequestParam("pageSize") int pageSize,
                                      HttpServletRequest request) {
        String token = request.getHeader("token");
        int offset = (page - 1) * pageSize;
        if (token == null || token.equals("")) {
            if (status == 0) {
                return ResultUtils.success(postService.postList(0, pageSize, offset));
            } else {
                return ResultUtils.success(null);
            }
        } else {
            int userId = JwtUtils.getIdInRequest(request);
            if (status == 1)
                return ResultUtils.success(postService.postLikeList(userId, pageSize, offset));
            else if (status == 2)
                return ResultUtils.success(postService.mypostList(userId, pageSize, offset));
            else
                return ResultUtils.success(postService.postList(userId, pageSize, offset));
        }
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
        if (status.equals("is_like")) {
            return ResultUtils.success(postService.unLike(postId, uid));
        } else if (status.equals("is_unlike")) {
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
