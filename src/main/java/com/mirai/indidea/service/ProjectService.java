package com.mirai.indidea.service;

import com.mirai.indidea.entity.Project;

public interface ProjectService {
    Project findProject(Integer cid, Integer id);
    Object findByUserId(Integer id);
}
