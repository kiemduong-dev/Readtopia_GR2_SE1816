//package controller.book;
//
//import dao.BookDAO;
//import dto.BookDTO;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.*;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet("/admin/book/list")
//public class BookListDashboardServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String keyword = request.getParameter("keyword");
//
//        BookDAO dao = new BookDAO();
//        List<BookDTO> books;
//
//        if (keyword != null && !keyword.trim().isEmpty()) {
//            books = dao.searchBooksByTitleOrAuthor(keyword.trim());
//        } else {
//            books = dao.getAllBooks();
//        }
//
//        request.setAttribute("bookList", books);
//        request.setAttribute("keyword", keyword);  // Giữ lại từ khóa trên ô search
//        request.getRequestDispatcher("/WEB-INF/view/admin/book/list.jsp").forward(request, response);
//    }
//}
