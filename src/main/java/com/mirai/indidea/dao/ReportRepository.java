package com.mirai.indidea.dao;

import com.mirai.indidea.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    List<Report> findReportsByStatus(Integer status);
    Report findReportById(Integer id);
}
