package controller.category;

import dao.CategoryDAO;
import dto.CategoryDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/category/edit")
public class CategoryEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CategoryDAO dao = new CategoryDAO();
        CategoryDTO category = dao.getCategoryById(id);
        request.setAttribute("category", category);
        request.getRequestDispatcher("/WEB-INF/view/admin/category/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        String categoryName = request.getParameter("categoryName");
        String description = request.getParameter("description");

        CategoryDAO dao = new CategoryDAO();
        dao.updateCategory(new CategoryDTO(categoryID, categoryName, description));

        response.sendRedirect(request.getContextPath() + "/admin/category/list");
    }
}