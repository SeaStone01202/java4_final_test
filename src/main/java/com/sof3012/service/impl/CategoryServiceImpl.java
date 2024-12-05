package com.sof3012.service.impl;

import com.sof3012.entity.Category;
import com.sof3012.repository.CategoryRepo;
import com.sof3012.repository.impl.CategoryRepoImpl;
import com.sof3012.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryRepo categoryRepo;

    public CategoryServiceImpl() {
        this.categoryRepo = new CategoryRepoImpl(Category.class);
    }

    @Override
    public Category create(Category e) {
        return categoryRepo.create(e);
    }

    @Override
    public Category update(Category e) {
        return categoryRepo.update(e);
    }

    @Override
    public Category delete(Category e) {
        return categoryRepo.delete(e);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepo.findAll(false);
    }

    @Override
    public Category getById(String id) {
        return categoryRepo.findById(id);
    }

    @Override
    public List<Category> findByName(String name) {
        return categoryRepo.findByName(name);
    }
}
