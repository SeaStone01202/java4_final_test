package com.sof3012.repository.impl;

import com.sof3012.entity.Category;
import com.sof3012.entity.Product;
import com.sof3012.repository.ProductRepo;

import java.util.List;

public class ProductRepoImpl extends GenericRepositoryImpl<Product, Integer> implements ProductRepo {
    public ProductRepoImpl(Class<Product> entityClass) {
        super(entityClass);
    }

    public List<Product> getByCategoryId(String categoryId) {
        String sql = "SELECT e FROM Product e WHERE e.category.id = ?1";
        return super.findMany(sql, categoryId);
    }
}
