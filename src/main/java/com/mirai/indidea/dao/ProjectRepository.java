package com.mirai.indidea.dao;

import com.mirai.indidea.entity.Project;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.xml.crypto.Data;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findAll();

    List<Project> findProjectsByStatusNotAndTitleLike(Integer status, String title);
    List<Project> findProjectsByStatusNotAndTitleLikeAndCategoryId(Integer status, String title, Integer category_id);

    List<Project> findProjectByCategoryId(Integer id);

    Project findProjectById(Integer id);

    List<Project> findProjectByOwnerIdAndStatusNot(Integer id, Integer status);

    Project findProjectByStatus(Integer status);

    List<Project> findProjectsByStatus(Integer status);
    List<Project> findProjectsByStatusAndCategoryId(Integer status, Integer category_id);

    List<Project> findTop9ByStatusAndTargetdateAfterOrderByOnlinetimeDesc(Integer status, Date targetdate);

    List<Project> findProjectsByStatusAndTitleLike(Integer status, String title);
    List<Project> findProjectsByStatusAndTitleLikeAndCategoryId(Integer status, String title, Integer category_id);

    @Query(nativeQuery = true, value = "SELECT owner_id,sum(targetpoint) FROM indidea.project group by owner_id;")
    List<Map<String,Object>> test();

    List<Project> findTop12ByStatusAndTargetdateAfterOrderByHittimeDesc(Integer status, Date date);

    Project findProjectByIdAndStatusOrStatusOrStatus(int id, Integer status, Integer status2, Integer status3);

    Project findByIdAndStatusIn(int id, Collection<Integer> status);

    Project findProjectByIdAndStatusOrStatusOrStatusOrStatusAndOwnerId(int id, Integer status, Integer status2, Integer status3, Integer status4, int ownerId);

    Project findByIdAndOwnerIdAndStatusIn(int id, int owner_id, Collection<Integer> status);

    Project findProjectByIdAndStatusNotInAndOwnerId(int id, Collection<Integer> status, int owner_id);

    Integer countProjectsByCategoryId(int category_id);

    /** 搜索
     * 搜索
     */
    List<Project> findByTitleContainsAndCategoryIdAndStatusIn(String title, Integer category_id, Collection<Integer> status, Pageable pageable);
    List<Project> findByTitleContainsAndStatusIn(String title, Collection<Integer> status, Pageable pageable);

    Integer countByTitleContainsAndCategoryIdAndStatusIn(String title, Integer category_id, Collection<Integer> status);
    Integer countByTitleContainsAndStatusIn(String title, Collection<Integer> status);
}
