package com.sof3012.controller;

import com.sof3012.entity.Category;
import com.sof3012.entity.Product;
import com.sof3012.service.CategoryService;
import com.sof3012.service.ProductService;
import com.sof3012.service.impl.CategoryServiceImpl;
import com.sof3012.service.impl.ProductServiceImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/product")
public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService = new ProductServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categoryList = categoryService.getAll();
        req.setAttribute("categoryList", categoryList);

        List<Product> productList = productService.getAll();
        req.setAttribute("productList", productList);
        req.getRequestDispatcher("product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selectedCategory = req.getParameter("categorySelected");

        List<Product> productList;
        if (selectedCategory.equals("all")) {
            productList = productService.getAll();
            req.setAttribute("message", "Đã chọn toàn bộ danh mục");
        } else {
            Category category = categoryService.getById(selectedCategory);
            if (category != null) {
                productList = productService.getByCategoryId(selectedCategory);
                req.setAttribute("message", "Danh mục được chọn: " + category.getName());
            } else {
                productList = productService.getAll();
                req.setAttribute("message", "Danh mục không tồn tại, hiển thị toàn bộ sản phẩm!");
            }
        }

        req.setAttribute("productList", productList);

        List<Category> categoryList = categoryService.getAll();
        req.setAttribute("categoryList", categoryList);

        req.getRequestDispatcher("product.jsp").forward(req, resp);
    }

}
