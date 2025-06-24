package controller.category;

import dao.CategoryDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/category/delete")
public class CategoryDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            CategoryDAO dao = new CategoryDAO();
            dao.deleteCategory(id);
        } catch (Exception e) {
            e.printStackTrace();
            // Optionally: set error message to request
        }

        response.sendRedirect(request.getContextPath() + "/admin/category/list");
    }
}
