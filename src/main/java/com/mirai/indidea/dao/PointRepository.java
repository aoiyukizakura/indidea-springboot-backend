package com.mirai.indidea.dao;

import com.mirai.indidea.entity.Point;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Integer> {
    List<Point> findByUserIdOrderByUpdatedatDesc(int user_id);
    List<Point> findByUserIdOrderByUpdatedatDesc(int user_id, Pageable pageable);
    Point findById(int id);
}
