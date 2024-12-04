package com.sof3012.controller;

import com.sof3012.entity.Category;
import com.sof3012.service.CategoryService;
import com.sof3012.service.impl.CategoryServiceImpl;

import java.util.List;

public class test {
    public static void main(String[] args) {
        CategoryService categoryService = new CategoryServiceImpl();

        List<Category> categoryList = categoryService.getAll();
        for (Category category : categoryList) {
            System.out.println(category.getId());
        }
    }
}
