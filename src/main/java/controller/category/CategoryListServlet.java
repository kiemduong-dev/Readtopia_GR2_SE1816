/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.category;

import dao.CategoryDAO;
import dto.CategoryDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author ngtua
 */
@WebServlet(name = "CategoryListServlet", urlPatterns = {"/admin/category/list"})
public class CategoryListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");

        CategoryDAO dao = new CategoryDAO();
        List<CategoryDTO> categoryList;

        if (keyword != null && !keyword.trim().isEmpty()) {
            categoryList = dao.searchCategories(keyword.trim());
        } else {
            categoryList = dao.getAllCategories();
        }

        request.setAttribute("categoryList", categoryList);
        request.setAttribute("keyword", keyword); // giữ lại giá trị search

        request.getRequestDispatcher("/WEB-INF/view/admin/category/list.jsp").forward(request, response);
    }
}
