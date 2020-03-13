package com.mirai.indidea.service;

import com.mirai.indidea.entity.*;

import java.util.List;

public interface AdminService {
    Admin login(String name, String pass);
    Admin find(int id);

    boolean doUser(int userId, int flag);
    boolean doUserAuth(int applyId, String adminname, int status);

    boolean doReport(int reportId, int flag, String admin);
    List<Report> allReport(Integer status);

    boolean doProject(int projectId, int flag);
    List<Project> allProject(int status, String title, Integer categoryId);

    boolean doPost(int postId, int flag);
    List<Post> allPost(int status);
    boolean doPostComment(int commentId, int status);
    List<Postcomment> allCommentByPostId(int postId);
    int commentTotal(int postId);

    boolean doCategories(String name);
    List<Category> allCategories();


    List<User> allUser(int status, String username);
    List<User> allUser(String username);

    List<Apply> allApply(int status, String admin);
    List<Apply> allApply();

}
