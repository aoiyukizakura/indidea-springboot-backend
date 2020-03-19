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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

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
    public Project findProject(Integer id) {
        Project p = projectRepository.findProjectById(id);
        p.setHittime(p.getHittime() + 1);
        projectRepository.saveAndFlush(p);
        return p;
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
    public Project create(Integer userId, UpdateProjectDto projectDto) {
        User user = userRepository.findUserById(userId);
        Category category = categoryRepository.findCategoryById(projectDto.getCategoryId());
        Project project = new Project();
        project.setOwner(user);
        project.setCategory(category);
        project.setSubtitle(projectDto.getSubtitle());
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
        return projectRepository.findTop9ByStatusAndTargetdateAfterOrderByUpdatedatDesc(1, new Date());
    }

    @Override
    public List<Map<String, Object>> test() {
        return projectRepository.test();
    }

    @Override
    public Project FeaturedProject() {
        return projectRepository.findProjectByStatus(5);
    }

    @Override
    public List<Project> topHitProject() {
        return projectRepository.findTop12ByStatusAndTargetdateAfterOrderByHittimeDesc(1, new Date());
    }

    @Override
    public Project getProjectDetail(int projectId) {
        Project p = projectRepository.findProjectByIdAndStatusOrStatusOrStatus(projectId, 1, 5, 6);
        p.setHittime(p.getHittime() + 1);
        return projectRepository.saveAndFlush(p);
    }

    @Override
    public Project getEditProject(int projectId, int ownerId) {
        return projectRepository.findProjectByIdAndStatusOrStatusOrStatusOrStatusAndOwnerId(projectId, 0, 2, 3, 7, ownerId);
    }

    @Override
    public Project waitCheckProject(int projectId) {
        Project p = projectRepository.findProjectById(projectId);
        p.setStatus(7);
        return projectRepository.saveAndFlush(p);
    }

    @Override
    public Project backToEdit(Integer projectId) {
        Project p = projectRepository.findProjectById(projectId);
        p.setStatus(0);
        return projectRepository.saveAndFlush(p);
    }

    @Override
    public Project sendProject(Integer projectId) {
        Project p = projectRepository.findProjectById(projectId);
        p.setStatus(1);
        return projectRepository.saveAndFlush(p);


    }

    /**
     * chaxun
     */
    @Override
    public List<Project> search(String title, Integer category_id, Pageable pageable, Integer status) {
        Integer[] inner_status;

        if (status == 1) {
            inner_status = new Integer[]{1, 5};
        } else if (status == 0){
            inner_status = new Integer[]{6};
        } else {
            return null;
        }
        try {
            if (category_id != null) {
                return projectRepository.findByTitleContainsAndCategoryIdAndStatusIn(title, category_id, Arrays.asList(inner_status), pageable);
            } else {
                return projectRepository.findByTitleContainsAndStatusIn(title, Arrays.asList(inner_status), pageable);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer count(String title, Integer category_id, Integer status) {
        Integer[] out_status;
        if (status == 1) {
            out_status = new Integer[]{1, 5};
        } else if (status == 0){
            out_status = new Integer[]{6};
        } else {
            return null;
        }
        try {
            if (category_id != null) {
                return projectRepository.countByTitleContainsAndCategoryIdAndStatusIn(title, category_id, Arrays.asList(out_status));
            } else {
                return projectRepository.countByTitleContainsAndStatusIn(title, Arrays.asList(out_status));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }
}
