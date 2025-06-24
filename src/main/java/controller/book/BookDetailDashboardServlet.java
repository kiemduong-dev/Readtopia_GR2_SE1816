//package controller.book;
//
//import dao.BookDAO;
//import dto.BookDTO;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.*;
//import java.io.IOException;
//
//@WebServlet("/admin/book/detail")
//public class BookDetailDashboardServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String idParam = request.getParameter("id");
//        int bookID = 0;
//
//        try {
//            bookID = Integer.parseInt(idParam);
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        }
//
//        BookDAO dao = new BookDAO();
//        BookDTO book = dao.getBookByID(bookID);
//
//        if (book != null) {
//            request.setAttribute("book", book);
//            request.getRequestDispatcher("/WEB-INF/view/admin/book/detail.jsp").forward(request, response);
//        } else {
//            response.sendRedirect(request.getContextPath() + "/admin/book/list");
//        }
//    }
//}
