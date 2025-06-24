package controller.category;

import dao.CategoryDAO;
import dto.CategoryDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/admin/category/add")
public class CategoryAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Hiển thị form thêm danh mục
        request.getRequestDispatcher("/WEB-INF/view/admin/category/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy dữ liệu từ form
        String name = request.getParameter("categoryName");
        String description = request.getParameter("description");

        // Tạo đối tượng Category
        CategoryDTO category = new CategoryDTO();
        category.setCategoryName(name);
        category.setCategoryDescription(description); // ✅ dòng sửa

        // Gọi DAO để thêm vào DB
        CategoryDAO dao = new CategoryDAO();
        dao.addCategory(category);

        // Chuyển hướng về trang danh sách
        response.sendRedirect(request.getContextPath() + "/admin/category/list");
    }

}