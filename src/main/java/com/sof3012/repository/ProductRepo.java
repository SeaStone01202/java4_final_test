package com.sof3012.repository;

import com.sof3012.entity.Category;
import com.sof3012.entity.Product;

import java.util.List;

public interface ProductRepo extends GenericRepository<Product, Integer>{
    List<Product> getByCategoryId(String categoryId);
}
