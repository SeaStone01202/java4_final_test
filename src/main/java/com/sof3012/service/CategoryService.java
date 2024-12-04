package com.sof3012.service;

import com.sof3012.entity.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category e);
    Category update(Category e);
    Category delete(Category e);
    List<Category> getAll();
    Category getById(String id);
}
