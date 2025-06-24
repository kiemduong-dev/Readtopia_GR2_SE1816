//package controller.book;
//
//import dao.BookDAO;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.*;
//import java.io.IOException;
//
//@WebServlet("/admin/book/delete")
//public class DeleteBookServlet extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            int bookID = Integer.parseInt(request.getParameter("id"));
//
//            BookDAO dao = new BookDAO();
//            dao.deleteBook(bookID);
//
//            response.sendRedirect(request.getContextPath() + "/admin/book/list");
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendRedirect(request.getContextPath() + "/WEB-INF/admin/book/list?error=delete_failed");
//        }
//    }
//}
