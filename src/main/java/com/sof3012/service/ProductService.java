package com.sof3012.service;

import com.sof3012.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Product create(Product e);
    Product update(Product e);
    Product delete(Product e);
    List<Product> getAll();
    Product getById(int id);
    List<Product> getByCategoryId(String productName);
    Map<String, Long> getCategoryProductCounts();
}
