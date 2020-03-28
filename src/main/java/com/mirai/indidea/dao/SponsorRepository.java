package com.mirai.indidea.dao;

import com.mirai.indidea.entity.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SponsorRepository extends JpaRepository<Sponsor, Integer> {
    long countByProjectId(int project_id);
    List<Sponsor> findByProjectId(int id);

    long countByProjectIdAndSponsorId(int project_id, int sponsor_id);

    long countBySponsorId(int sponsor_id);
    List<Sponsor> findBySponsorId(int sponsor_id);
}
