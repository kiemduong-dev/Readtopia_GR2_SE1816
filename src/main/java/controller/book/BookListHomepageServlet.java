package controller.book;

import dao.BookDAO;
import dao.CategoryDAO;
import dto.BookDTO;
import dto.CategoryDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/book/home")
public class BookListHomepageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");
        String catIDStr = request.getParameter("catID");
        String sortBy = request.getParameter("sortBy");

        int catID = 0;
        try {
            if (catIDStr != null && !catIDStr.isEmpty()) {
                catID = Integer.parseInt(catIDStr);
            }
        } catch (NumberFormatException ignored) {
        }

        BookDAO bookDAO = new BookDAO();
        List<BookDTO> books;

        // Nếu có từ khóa tìm kiếm thì tìm theo từ khóa
        if (keyword != null && !keyword.trim().isEmpty()) {
            books = bookDAO.searchBooksByTitleOrAuthor(keyword.trim());

        // Nếu không có từ khóa, lọc theo danh mục nếu chọn
        } else if (catID > 0) {
            books = bookDAO.getBooksByCategory(catID);

        // Nếu không có từ khóa và danh mục, sắp xếp theo yêu cầu
        } else if (sortBy != null && !sortBy.isEmpty()) {
            books = bookDAO.getBooksSortedBy(sortBy);

        // Nếu không có gì thì lấy tất cả sách
        } else {
            books = bookDAO.getAllBooks();
        }

        CategoryDAO categoryDAO = new CategoryDAO();
        List<CategoryDTO> categories = categoryDAO.getAllCategories();

        // Gửi dữ liệu xuống JSP
        request.setAttribute("bookList", books);
        request.setAttribute("categoryList", categories);
        request.setAttribute("keyword", keyword);
        request.setAttribute("selectedCatID", catID);
        request.setAttribute("sortBy", sortBy);

        request.getRequestDispatcher("/book/homepage.jsp").forward(request, response);
    }
}
