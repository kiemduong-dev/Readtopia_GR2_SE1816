package controller.book;

import dao.BookDAO;
import dto.BookDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/book/sort")
public class SortBookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sortBy = request.getParameter("sortBy"); // ví dụ: "price_asc", "title_desc"
        BookDAO dao = new BookDAO();

        List<BookDTO> sortedBooks = dao.getBooksSortedBy(sortBy);

        request.setAttribute("bookList", sortedBooks);
        request.setAttribute("sortBy", sortBy); // dùng để giữ trạng thái đã chọn

        request.getRequestDispatcher("/book/homepage.jsp").forward(request, response);
    }
}
