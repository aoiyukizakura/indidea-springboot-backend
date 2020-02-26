package com.mirai.indidea.service;



import com.mirai.indidea.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findOne(int id);
}
