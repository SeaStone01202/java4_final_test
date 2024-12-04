package com.sof3012.service.impl;

import com.sof3012.entity.Category;
import com.sof3012.entity.Product;
import com.sof3012.repository.CategoryRepo;
import com.sof3012.repository.ProductRepo;
import com.sof3012.repository.impl.CategoryRepoImpl;
import com.sof3012.repository.impl.ProductRepoImpl;
import com.sof3012.service.ProductService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    public ProductServiceImpl() {
        this.productRepo = new ProductRepoImpl(Product.class);
        this.categoryRepo = new CategoryRepoImpl(Category.class);
    }


    @Override
    public Product create(Product e) {
        return null;
    }

    @Override
    public Product update(Product e) {
        return null;
    }

    @Override
    public Product delete(Product e) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return productRepo.findAll(false);
    }

    @Override
    public Product getById(int id) {
        return null;
    }

    @Override
    public List<Product> getByCategoryId(String productName) {
        return productRepo.getByCategoryId(productName);
    }

    @Override
    public Map<String, Long> getCategoryProductCounts() {
        // Lấy tất cả danh mục từ CategoryRepo
        List<Category> categories = categoryRepo.findAll(false);  // Giả sử categoryRepo là đối tượng truy xuất danh mục

        // Lấy tất cả sản phẩm từ ProductRepo
        List<Product> products = getAll();  // Giả sử getAll() là phương thức trả về tất cả sản phẩm

        // Tính số lượng sản phẩm theo từng danh mục
        Map<String, Long> productCounts = products.stream()
                .collect(Collectors.groupingBy(
                        product -> product.getCategory().getName(),
                        Collectors.counting()
                ));

        // Duyệt qua tất cả các danh mục và gán số lượng 0 cho danh mục không có sản phẩm
        for (Category category : categories) {
            productCounts.putIfAbsent(category.getName(), 0L);
        }

        return productCounts;
    }


}
