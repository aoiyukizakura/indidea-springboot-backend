package com.mirai.indidea.service.serviceImpl;

import com.mirai.indidea.dao.*;
import com.mirai.indidea.dto.ProjectDto.UpdateProjectDto;
import com.mirai.indidea.entity.*;
import com.mirai.indidea.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
@Slf4j
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SponsorRepository sponsorRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private PointRepository pointRepository;
    @Autowired
    private RewardRepository rewardRepository;
    @Autowired
    private ProjectquzRepository projectquzRepository;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private MsgboardRepository msgboardRepository;
    @Autowired
    private ReportRepository reportRepository;
    @Override
    public Project findProject(Integer id) {
        Project p = projectRepository.findProjectById(id);
        p.setHittime(p.getHittime() + 1);
        projectRepository.saveAndFlush(p);
        return p;
    }

    @Override
    public Project findById(Integer id) {
        return projectRepository.findProjectById(id);
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
        return projectRepository.findTop9ByStatusAndTargetdateAfterOrderByOnlinetimeDesc(1, new Date());
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
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(5);
        integers.add(6);
        Project p = projectRepository.findByIdAndStatusIn(projectId, integers);
        p.setHittime(p.getHittime() + 1);
        return projectRepository.saveAndFlush(p);
    }

    @Override
    public Project getEditProject(int projectId, int ownerId) {
        List<Integer> integers = new ArrayList<>();
        integers.add(0);
        integers.add(2);
        integers.add(3);
        integers.add(7);
        return projectRepository.findByIdAndOwnerIdAndStatusIn(projectId, ownerId, integers);
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
        p.setOnlinetime(new Timestamp(new Date().getTime()));
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

    /**
     * 详情
     */
    @Override
    public long countSponsor(Integer projectId) {
        return sponsorRepository.countByProjectId(projectId);
    }
    @Override
    public List<Sponsor> sponsor(int projectId) {
        return sponsorRepository.findByProjectId(projectId);
    }

    @Override
    public boolean saveProject(int projectId, int userId) {
        try {
            Project project = projectRepository.findProjectById(projectId);
            User user = userRepository.findUserById(userId);
            if (project != null && user != null) {
                Favorite favorite = new Favorite();
                favorite.setProject(project);
                favorite.setUser(user);
                favoriteRepository.saveAndFlush(favorite);
                return true;
            } else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteSave(int projectId, int userId) {
        try {
            favoriteRepository.deleteByProjectIdAndUserId(projectId, userId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean saveStatus(int projectId, int userId) {
        return favoriteRepository.findByProjectIdAndUserId(projectId, userId) != null;
    }

    /**
     *
     * @param projectId 项目id
     * @param userId 用户id
     * @param point 实际捐献
     * @param rewardId 用的哪个奖励阶梯 0则没有选择奖励阶梯(白给
     * @return boolean
     */
    @Override
    public boolean supportProject(int projectId, int userId, int point, int rewardId) {
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        User user = userRepository.findUserById(userId);
        Project project = projectRepository.findProjectById(projectId);
        Reward reward = null;
        if (rewardId > 0) {
            reward = rewardRepository.findById(rewardId);
            if (reward.getPoint() > point) return false;
        }
        if (point<=0) {
            return false;
        }
        Integer balance = user.getBalance();
        Integer projectPoint = project.getGetpoint();
        String order = "support-project-" + project.getId() + "-user-" + user.getId() + "-" + ft.format(dNow);
        if (balance >= point) {
            try {
                balance -= point;
                projectPoint += point;
                user.setBalance(balance);
                project.setGetpoint(projectPoint);
                Sponsor sponsor = new Sponsor(user, project, point, reward);
                Point p = new Point(-point, user, order);
                userRepository.saveAndFlush(user);
                projectRepository.saveAndFlush(project);
                pointRepository.saveAndFlush(p);
                sponsorRepository.saveAndFlush(sponsor);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<Projectquz> quzList(int projectId) {
        return projectquzRepository.findByStatusAndProjectIdOrderByUpdatedatDesc(1, projectId);
    }

    @Override
    public boolean addQuz(int user_id, String quzcontent, int project_id) {
        try {
            User user = new User();
            user.setId(user_id);
            Project project = new Project();
            project.setId(project_id);
            Projectquz projectquz = new Projectquz();
            projectquz.setProject(project);
            projectquz.setQuser(user);
            projectquz.setQuzcontent(quzcontent);
            projectquzRepository.saveAndFlush(projectquz);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Projectquz> waitReply(int id) {
        try {
            return projectquzRepository.findByStatusAndProjectId(0, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean replyQuz(int userId, String reply, int quzId) {
        try {
            Projectquz projectquz = projectquzRepository.findById(quzId);
            if (projectquz.getProject().getOwner().getId() != userId) {
                return false;
            }
            projectquz.setAncontent(reply);
            projectquz.setStatus(1);
            projectquzRepository.saveAndFlush(projectquz);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Log> logList(int projectId) {
        return logRepository.findByProjectIdAndStatusOrderByNumberDesc(projectId, 1);
    }

    @Override
    public boolean updateLog(int projectId, String title, String content, int userId) {
        try {
            int number = logRepository.countByProjectId(projectId) + 1;
            Log log = new Log();
            log.setContent(content);
            log.setTitle(title);
            log.setNumber(number);
            Project project = new Project();
            project.setId(projectId);
            log.setProject(project);
            logRepository.saveAndFlush(log);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Msgboard> msgList(int projectId) {
        return msgboardRepository.findByProjectIdAndStatusOrderByCreatedatDesc(projectId, 1);
    }

    @Override
    public boolean addMsg(int projectId, int user_id, String content) {
        try {
            long sponsor = sponsorRepository.countByProjectIdAndSponsorId(projectId, user_id);
            if (sponsor > 0) {
                Project project = new Project(projectId);
                User user = new User(user_id);
                Msgboard msgboard = new Msgboard(user, project, content);
                msgboardRepository.saveAndFlush(msgboard);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean report(int userId, String content, int projectId) {
        if (content.equals("")) {
            return false;
        }
        try {
            Report report = new Report();
            Project project = new Project();
            project.setId(projectId);
            User user = new User();
            user.setId(userId);
            report.setProject(project);
            report.setReporter(user);
            report.setContent(content);
            reportRepository.saveAndFlush(report);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(int projectId, int idInRequest) {
        Project p = projectRepository.findProjectById(projectId);
        if (p.getOwner().getId() == idInRequest) {
            try {
                projectRepository.delete(p);
                projectRepository.flush();
                return true;
            }catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<Sponsor> sponsorList(int projectId) {
        return sponsorRepository.findByProjectId(projectId);
    }
}
