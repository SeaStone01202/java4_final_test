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
import java.util.ArrayList;
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
        String categoryName = req.getParameter("categoryName");

        List<Product> productList = new ArrayList<>();

        // Kiểm tra nếu người dùng chọn danh mục
        if (selectedCategory != null && !selectedCategory.equals("all")) {
            // Tìm theo danh mục đã chọn
            Category category = categoryService.getById(selectedCategory);
            if (category != null) {
                productList = productService.getByCategoryId(selectedCategory);
                req.setAttribute("message", "Danh mục được chọn: " + category.getName());
            } else {
                productList = productService.getAll(); // Nếu danh mục không tồn tại, hiển thị tất cả sản phẩm
                req.setAttribute("message", "Danh mục không tồn tại, hiển thị toàn bộ sản phẩm!");
            }
        } else if (categoryName != null && !categoryName.trim().isEmpty()) {
            // Tìm theo tên danh mục nếu có nhập vào
            List<Category> categories = categoryService.findByName(categoryName);
            if (categories != null && !categories.isEmpty()) {
                List<Product> categoryProducts = new ArrayList<>();
                for (Category category : categories) {
                    categoryProducts.addAll(productService.getByCategoryId(category.getId()));
                }
                productList = categoryProducts;
                req.setAttribute("message", "Danh mục có tên chứa '" + categoryName + "' được tìm thấy.");
            } else {
                productList = productService.getAll();
                req.setAttribute("message", "Không tìm thấy danh mục với tên: " + categoryName + ", hiển thị toàn bộ sản phẩm!");
            }
        } else {
            // Nếu không chọn gì, hiển thị toàn bộ sản phẩm
            productList = productService.getAll();
            req.setAttribute("message", "Đã chọn toàn bộ danh mục");
        }

        // Gửi danh sách sản phẩm và danh mục đến trang JSP
        req.setAttribute("productList", productList);

        // Lấy tất cả danh mục để hiển thị trong dropdown
        List<Category> categoryList = categoryService.getAll();
        req.setAttribute("categoryList", categoryList);

        req.getRequestDispatcher("product.jsp").forward(req, resp);
    }


}
