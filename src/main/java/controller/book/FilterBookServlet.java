//package controller.book;
//
//import dao.BookDAO;
//import dao.CategoryDAO;
//import dto.BookDTO;
//import dto.CategoryDTO;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.*;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet("/book/filter")
//public class FilterBookServlet extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            int categoryId = Integer.parseInt(request.getParameter("catID"));
//
//            BookDAO dao = new BookDAO();
//            CategoryDAO catDao = new CategoryDAO();
//
//            List<BookDTO> books = dao.getBooksByCategory(categoryId);
//            List<CategoryDTO> categories = catDao.getAllCategories();
//
//            request.setAttribute("bookList", books);
//            request.setAttribute("categoryList", categories);
//            request.setAttribute("selectedCatID", categoryId); // ✅ để đánh dấu selected đúng
//
//            request.getRequestDispatcher("/book/homepage.jsp").forward(request, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendRedirect(request.getContextPath() + "/book/home");
//        }
//    }
//}
