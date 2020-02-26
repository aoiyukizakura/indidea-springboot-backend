package com.mirai.indidea.service.serviceImpl;

import com.mirai.indidea.dao.CategoryRepository;
import com.mirai.indidea.entity.Category;
import com.mirai.indidea.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findOne(int id) {
        return categoryRepository.findCategoryById(id);
    }
}
