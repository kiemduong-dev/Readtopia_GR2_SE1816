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
//@WebServlet("/admin/book/add")
//public class AddBookServlet extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        // Lấy danh mục để truyền xuống JSP
//        CategoryDAO categoryDAO = new CategoryDAO();
//        List<CategoryDTO> categories = categoryDAO.getAllCategories();
//        request.setAttribute("categoryList", categories);
//
//        // Chuyển đến trang thêm sách
//        request.getRequestDispatcher("/WEB-INF/view/admin/book/add.jsp").forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        try {
//            BookDTO book = new BookDTO();
//
//            book.setBookTitle(request.getParameter("title"));
//            book.setAuthor(request.getParameter("author"));
//            book.setTranslator(request.getParameter("translator"));
//            book.setPublisher(request.getParameter("publisher"));
//            book.setPublicationYear(Integer.parseInt(request.getParameter("year")));
//            book.setIsbn(request.getParameter("isbn"));
//            book.setImage(request.getParameter("image"));
//            book.setBookDescription(request.getParameter("description"));
//            book.setHardcover(Integer.parseInt(request.getParameter("hardcover")));
//            book.setDimension(request.getParameter("dimension"));
//            book.setWeight(Float.parseFloat(request.getParameter("weight")));
//            book.setBookPrice(Double.parseDouble(request.getParameter("price")));
//
//            // Gán mặc định
//            book.setBookQuantity(1);
//            book.setBookStatus(1);
//
//            BookDAO dao = new BookDAO();
//            dao.insertBook(book);
//
//            response.sendRedirect(request.getContextPath() + "/admin/book/list");
//
//        } catch (IOException | NumberFormatException e) {
//            request.setAttribute("error", "❌ Lỗi thêm sách!");
//            // Nếu có lỗi, cần gọi lại danh mục để dropdown vẫn hiển thị được
//            CategoryDAO categoryDAO = new CategoryDAO();
//            List<CategoryDTO> categories = categoryDAO.getAllCategories();
//            request.setAttribute("categoryList", categories);
//
//            request.getRequestDispatcher("/WEB-INF/view/admin/book/add.jsp").forward(request, response);
//        }
//    }
//}
