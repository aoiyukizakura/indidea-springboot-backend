package com.mirai.indidea.service.serviceImpl;

import com.mirai.indidea.dao.ProjectRepository;
import com.mirai.indidea.entity.Project;
import com.mirai.indidea.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public Project findProject(Integer cid, Integer id) {
        return projectRepository.findProjectByCategoryAndId(cid,id);
    }

    @Override
    public Object findByUserId(Integer id) {
        return projectRepository.findByUserId(id);
    }
}
