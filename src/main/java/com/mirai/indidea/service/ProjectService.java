package com.mirai.indidea.service;

import com.mirai.indidea.dto.ProjectDto.UpdateProjectDto;
import com.mirai.indidea.entity.*;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    Project findProject(Integer id);
    Project findById(Integer id);
    Object findByUserId(Integer id);
    List<Project> findAll(Integer id);
    Project create(Integer userId, UpdateProjectDto projectDto);
    Project update(UpdateProjectDto projectDto);
    List<Project> top9Project();
    List<Map<String,Object>> test();
    Project FeaturedProject();
    List<Project> topHitProject();
    Project getProjectDetail(int projectId);
    Project getEditProject(int projectId, int ownerId);
    Project waitCheckProject(int projectId);

    Project backToEdit(Integer projectId);

    Project sendProject(Integer projectId);

    /**
     * 查询
     */
    List<Project> search(String title, Integer category_id, Pageable pageable, Integer status);
    Integer count(String title, Integer category_id, Integer status);

    /**
     * 详情
     */
    long countSponsor(Integer projectId);
    List<Sponsor> sponsor(int projectId);

    boolean saveProject(int projectId, int userId);
    boolean deleteSave(int projectId, int userId);
    boolean saveStatus(int projectId, int userId);

    boolean supportProject(int projectId, int userId, int point, int rewardId);

    /**
     * quz
     */
    List<Projectquz> quzList(int projectId);
    boolean addQuz(int user_id, String quzcontent, int project_id);
    boolean replyQuz(int userId, String reply, int quzId);

    List<Log> logList(int projectId);

    List<Msgboard> msgList(int projectId);

    boolean addMsg(int projectId, int user_id, String content);

    boolean report(int userId, String content, int projectId);

    boolean updateLog(int projectId, String title, String content, int userId);

    List<Projectquz> waitReply(int id);

    boolean delete(int projectId, int idInRequest);

    List<Sponsor> sponsorList(int projectId);
}
