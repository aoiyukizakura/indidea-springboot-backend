package com.mirai.indidea.dao;

import com.mirai.indidea.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findAll();
    List<Project> findProjectByCategoryId(Integer id);
    Project findProjectById(Integer id);
    List<Project> findProjectByOwnerId(Integer id);
    Project findProjectByStatus(Integer status);
    List<Project> findTop9ByStatusAndTargetdateAfterOrderByUpdatedatDesc(Integer status, Date targetdate);

    @Query(nativeQuery = true, value = "SELECT owner_id,sum(targetpoint) FROM indidea.project group by owner_id;")
    List<Map<String,Object>> test();

    List<Project> findTop12ByStatusAndTargetdateAfterOrderByHittimeDesc(Integer status, Date date);
    Project findProjectByIdAndStatusOrStatusOrStatus(int id, Integer status, Integer status2, Integer status3);
    Project findProjectByIdAndStatusOrStatusOrStatusOrStatusAndOwnerId(int id, Integer status, Integer status2, Integer status3, Integer status4, int ownerId);
}
