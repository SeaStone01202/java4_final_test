package com.sof3012.repository.impl;

import com.sof3012.entity.Category;
import com.sof3012.repository.CategoryRepo;

import java.util.List;

public class CategoryRepoImpl extends GenericRepositoryImpl<Category, String> implements CategoryRepo {
    public CategoryRepoImpl(Class<Category> entityClass) {
        super(entityClass);
    }

    public List<Category> findByName(String name) {
        String sql = "SELECT e FROM Category e WHERE LOWER(e.name) LIKE LOWER(?1)";
        return super.findMany(sql, name);
    }
}
