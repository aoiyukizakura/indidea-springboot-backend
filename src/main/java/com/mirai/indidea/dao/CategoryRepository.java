package com.mirai.indidea.dao;


import com.mirai.indidea.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findCategoryById(Integer id);
}
