package com.sof3012.repository;

import com.sof3012.entity.Category;

import java.util.List;

public interface CategoryRepo extends GenericRepository<Category, String>{
    List<Category> findByName(String name);
}
