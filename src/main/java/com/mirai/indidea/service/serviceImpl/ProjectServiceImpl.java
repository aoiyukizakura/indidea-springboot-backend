package com.mirai.indidea.service.serviceImpl;

import com.mirai.indidea.dao.CategoryRepository;
import com.mirai.indidea.dao.ProjectRepository;
import com.mirai.indidea.dao.UserRepository;
import com.mirai.indidea.entity.Category;
import com.mirai.indidea.entity.Project;
import com.mirai.indidea.entity.User;
import com.mirai.indidea.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Project findProject(Integer cid, Integer id) {
        return projectRepository.findProjectByCategoryAndId(cid,id);
    }

    @Override
    public Object findByUserId(Integer id) {
        return null;
    }

    @Override
    public List<Project> findAll(Integer id) {
        return projectRepository.findProjectByCategoryId(id);
    }

    @Override
    public Project created(Integer userId, Integer categoryId) {
        User user = userRepository.findUserById(userId);
        Category category = categoryRepository.findCategoryById(categoryId);
        Project project = new Project();
        project.setOwner(user);
        project.setCategory(category);
        return projectRepository.saveAndFlush(project);
    }
}
