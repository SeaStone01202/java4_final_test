package com.sof3012.controller;

import com.sof3012.service.CategoryService;
import com.sof3012.service.ProductService;
import com.sof3012.service.impl.CategoryServiceImpl;
import com.sof3012.service.impl.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet("/statistics")
public class StatisticsController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Long> categoryCounts = productService.getCategoryProductCounts();
        req.setAttribute("categoryCounts", categoryCounts);
        req.getRequestDispatcher("statistics.jsp").forward(req, resp);
    }
}
