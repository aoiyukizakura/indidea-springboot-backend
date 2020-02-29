package com.mirai.indidea.service.serviceImpl;

import com.mirai.indidea.dao.CategoryRepository;
import com.mirai.indidea.dao.ProjectRepository;
import com.mirai.indidea.dao.UserRepository;
import com.mirai.indidea.dto.ProjectDto.UpdateProjectDto;
import com.mirai.indidea.entity.Category;
import com.mirai.indidea.entity.Project;
import com.mirai.indidea.entity.User;
import com.mirai.indidea.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
    public Project create(Integer userId, Integer categoryId) {
        User user = userRepository.findUserById(userId);
        Category category = categoryRepository.findCategoryById(categoryId);
        Project project = new Project();
        project.setOwner(user);
        project.setCategory(category);
        return projectRepository.saveAndFlush(project);
    }

    @Override
    public Project update(UpdateProjectDto projectDto) {
        int projectId = projectDto.getId();
        Project project = projectRepository.findProjectById(projectId);
        if (projectDto.getAddress() != null)
            project.setAddress(project.getAddress());
        if (projectDto.getCategoryId() != null)
            project.setCategory(categoryRepository.findCategoryById(projectDto.getCategoryId()));
        if (projectDto.getPerdate() != null)
            project.setPerdate(projectDto.getPerdate());
        if (projectDto.getPic() != null)
            project.setPic(projectDto.getPic());
        if (projectDto.getPublishlink() != null)
            project.setPublishlink(projectDto.getPublishlink());
        if (projectDto.getPublishtitle() != null)
            project.setPublishtitle(projectDto.getPublishtitle());
        if (projectDto.getStory() != null)
            project.setStory(projectDto.getStory());
        if (projectDto.getSubtitle() != null)
            project.setSubtitle(projectDto.getSubtitle());
        if (projectDto.getTargetdate() != null)
            project.setTargetdate(projectDto.getTargetdate());
        if (projectDto.getTargetpoint() != null)
            project.setTargetpoint(projectDto.getTargetpoint());
        if (projectDto.getVideo() != null)
            project.setVideo(projectDto.getVideo());
        if (projectDto.getTitle() != null)
            project.setTitle(projectDto.getTitle());
        return projectRepository.saveAndFlush(project);
    }

    @Override
    public List<Project> top9Project() {
        return projectRepository.findTop9ByStatusOrderByUpdatedatDesc(1);
    }

    @Override
    public List<Map<String,Object>> test() {
        return projectRepository.test();
    }
}
