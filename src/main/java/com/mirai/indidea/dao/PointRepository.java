package com.mirai.indidea.dao;

import com.mirai.indidea.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Integer> {
    List<Point> findByUserId(int user_id);
    Point findById(int id);
}
