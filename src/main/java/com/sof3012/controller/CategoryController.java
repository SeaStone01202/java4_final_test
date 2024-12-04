package com.sof3012.controller;

import com.sof3012.entity.Category;
import com.sof3012.service.CategoryService;
import com.sof3012.service.impl.CategoryServiceImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/category")
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("categoryId");

        if (id != null) {
            // Lấy category theo ID
            Category category = categoryService.getById(id);

            if (category != null) {
                req.setAttribute("editCategory", category);
            } else {
                req.setAttribute("message", "Không tìm thấy danh mục!");
            }
        }
        updateCategoryList(req);
        req.getRequestDispatcher("/category.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String categoryId = req.getParameter("categoryId");
        String categoryName = req.getParameter("categoryName");

        if (isNullOrEmpty(categoryId)) {
            setErrorAndForward(req, resp, "Chưa nhập Id!!!");
            return;
        }

        if (isNullOrEmpty(categoryName)) {
            setErrorAndForward(req, resp, "Chưa nhập tên thể loại");
            return;
        }

        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);

        switch (action) {
            case "create":
                handleCreateCategory(req, category);
                break;
            case "delete":
                handleDeleteCategory(req, categoryId);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/category");
                return;
        }

        updateCategoryList(req);
        req.getRequestDispatcher("/category.jsp").forward(req, resp);
    }

    private void handleCreateCategory(HttpServletRequest req, Category category) {
        if (categoryService.getById(category.getId()) != null) {
            req.setAttribute("message", "Category id đã tồn tại !");
        } else {
            Category createdCategory = categoryService.create(category);
            req.setAttribute("message", createdCategory != null ? "Tạo thành công!" : "Tạo thất bại!");
        }
    }

    private void handleDeleteCategory(HttpServletRequest req, String categoryId) {
        Category categoryToDelete = categoryService.getById(categoryId);
        if (categoryToDelete == null) {
            req.setAttribute("message", "Category Id chưa khởi tạo!");
        } else if (categoryToDelete.getProducts() != null && !categoryToDelete.getProducts().isEmpty()) {
            req.setAttribute("message", "Không thể xóa vì danh mục chứa sản phẩm!");
        } else {
            categoryService.delete(categoryToDelete);
            req.setAttribute("message", "Xóa thành công!");
        }
    }

    private void updateCategoryList(HttpServletRequest req) {
        List<Category> categoryList = categoryService.getAll();
        ServletContext context = req.getServletContext();
        context.setAttribute("categoryList", categoryList);
    }

    private void setErrorAndForward(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
        req.setAttribute("message", message);
        req.getRequestDispatcher("/category.jsp").forward(req, resp);
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
