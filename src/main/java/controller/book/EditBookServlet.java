package controller.book;

import dao.BookDAO;
import dto.BookDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/book/edit")
public class EditBookServlet extends HttpServlet {

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    String idParam = request.getParameter("id");

    // Kiểm tra null hoặc rỗng
    if (idParam == null || idParam.isEmpty()) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu tham số id!");
        return;
    }

    try {
        int id = Integer.parseInt(idParam);
        BookDAO dao = new BookDAO();
        BookDTO book = dao.getBookByID(id);

        if (book != null) {
            request.setAttribute("book", book);
            request.getRequestDispatcher("/WEB-INF/view/admin/book/edit.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/book/list");
        }

    } catch (NumberFormatException e) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ!");
    }
}


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            BookDTO book = new BookDTO();

            book.setBookID(id);
            book.setBookTitle(request.getParameter("title"));
            book.setAuthor(request.getParameter("author"));
            book.setTranslator(request.getParameter("translator"));
            book.setPublisher(request.getParameter("publisher"));
            book.setPublicationYear(Integer.parseInt(request.getParameter("year")));
            book.setIsbn(request.getParameter("isbn"));
            book.setImage(request.getParameter("image"));
            book.setBookDescription(request.getParameter("description"));
            book.setHardcover(Integer.parseInt(request.getParameter("hardcover")));
            book.setDimension(request.getParameter("dimension"));
            book.setWeight(Float.parseFloat(request.getParameter("weight")));
            book.setBookPrice(Double.parseDouble(request.getParameter("price")));
            book.setBookQuantity(Integer.parseInt(request.getParameter("quantity")));
            book.setBookStatus(Integer.parseInt(request.getParameter("status")));

            BookDAO dao = new BookDAO();
            dao.updateBook(book);

            response.sendRedirect(request.getContextPath() + "/admin/book/list");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "❌ Lỗi cập nhật sách!");
            request.getRequestDispatcher("/WEB-INF/view/admin/book/edit.jsp").forward(request, response);
        }
    }
}
