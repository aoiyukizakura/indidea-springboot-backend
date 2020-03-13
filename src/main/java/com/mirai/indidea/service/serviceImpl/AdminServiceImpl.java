package com.mirai.indidea.service.serviceImpl;

import com.mirai.indidea.dao.*;
import com.mirai.indidea.entity.*;
import com.mirai.indidea.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ApplyRepository applyRepository;

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostCommentRepository commentRepository;

    @Override
    public Admin login(String name, String pass) {
        return adminRepository.findAdminByAdminnameAndPassword(name, pass);
    }

    @Override
    public Admin find(int id) {
        return adminRepository.findAdminById(id);
    }

    @Override
    public boolean doUser(int userId, int flag) {
        try {
            User u = userRepository.findUserById(userId);
            u.setStatus(flag);
            userRepository.saveAndFlush(u);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean doUserAuth(int applyId, String adminname, int status) {
        try {
            Apply apply = applyRepository.findApplyById(applyId);
            apply.setEditby(adminname);
            apply.setStatus(status);
            if(status == 2) {
                doUser(apply.getUser().getId(), 2);
            }
            applyRepository.saveAndFlush(apply);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean doReport(int reportId, int flag, String admin) {
        try {
            if (flag == 2 || flag == 0) {
                Report report = reportRepository.findReportById(reportId);
                report.setStatus(flag);
                report.setEditby(admin);
                reportRepository.saveAndFlush(report);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean doProject(int projectId, int flag) {
        try {
            if (flag == 5) {
                Project p2 = projectRepository.findProjectByStatus(5);
                p2.setStatus(1);
                projectRepository.saveAndFlush(p2);
            }
            Project p = projectRepository.findProjectById(projectId);
            p.setStatus(flag);
            projectRepository.saveAndFlush(p);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }


    @Override
    public List<Post> allPost(int status) {
        if (status == 4) {
            List<Integer> list = Arrays.asList(2,3);
            return postRepository.findPostsByStatusNotIn(list);
        }
        return postRepository.findPostsByStatus(status);
    }

    @Override
    public boolean doPost(int postId, int flag) {
        try {
            Post post = postRepository.getOne(postId);
            post.setStatus(flag);
            postRepository.saveAndFlush(post);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean doPostComment(int commentId, int status) {
        try {
            Postcomment postcomment = commentRepository.getOne(commentId);
            postcomment.setStatus(status);
            commentRepository.saveAndFlush(postcomment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Postcomment> allCommentByPostId(int postId) {
        return commentRepository.findPostcommentsByPostId(postId);
    }

    @Override
    public int commentTotal(int postId) {
        return commentRepository.countByPostId(postId);
    }

    @Override
    public boolean doCategories(String name) {
        try {
            Category c = new Category();
            c.setName(name);
            categoryRepository.saveAndFlush(c);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Project> allProject(int status, String title, Integer categoryId) {
        Integer[] integers = new Integer[]{1,2,3,4,5,6,7,8};
        if (!Arrays.asList(integers).contains(status)) {
            return null;
        }
        if (categoryId != null) {
            if (status == 8)
                return projectRepository.findProjectsByStatusNotAndTitleLikeAndCategoryId(0, '%'+title+'%',categoryId);
            else {
                if (title.equals("")) {
                    return projectRepository.findProjectsByStatusAndCategoryId(status, categoryId);
                }
                return projectRepository.findProjectsByStatusAndTitleLikeAndCategoryId(status, '%'+title+'%', categoryId);
            }
        } else {
            if (status == 8)
                return projectRepository.findProjectsByStatusNotAndTitleLike(0, '%'+title+'%');
            else {
                if (title.equals("")) {
                    return projectRepository.findProjectsByStatus(status);
                }
                return projectRepository.findProjectsByStatusAndTitleLike(status, '%'+title+'%');
            }
        }
    }

    @Override
    public List<Report> allReport(Integer status) {
        if (status!= null)
            return reportRepository.findReportsByStatus(status);
        else
            return reportRepository.findAll();
    }

    @Override
    public List<User> allUser(int status, String username) {
        return userRepository.findByStatusAndUsernameLike(status, '%'+username+'%');
    }

    @Override
    public List<User> allUser(String username) {
        return userRepository.findByUsernameLike('%'+username+'%');
    }

    @Override
    public List<Apply> allApply(int status, String admin) {
        if(status == 1) {
            return applyRepository.findByStatus(status);
        }
        return applyRepository.findByStatusAndEditbyLike(status, '%'+admin+'%');
    }

    @Override
    public List<Apply> allApply() {
        return applyRepository.findAll();
    }


}
