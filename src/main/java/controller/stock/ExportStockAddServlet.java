package controller.stock;

import dao.BookDAO;
import dao.ExportStockDAO;
import dao.StaffDAO;
import dto.ExportStockDetailDTO;
import dto.ExportStockDTO;
import dto.StaffDTO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/admin/stock/export/exportStockAdd")
public class ExportStockAddServlet extends HttpServlet {
    private ExportStockDAO exportDAO;
    private StaffDAO staffDAO;
    private BookDAO bookDAO;

    @Override
    public void init() throws ServletException {
        exportDAO = new ExportStockDAO();
        staffDAO = new StaffDAO();
        bookDAO = new BookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<StaffDTO> staffList = staffDAO.getAllStaffs();
        request.setAttribute("staffList", staffList);
        request.setAttribute("bookList", bookDAO.getAllBooks());

        request.getRequestDispatcher("/WEB-INF/view/admin/stock/exportStockAdd.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String customerName = request.getParameter("customerName");
            String staffUsername = request.getParameter("staffName");
            Date exportDate = Date.valueOf(request.getParameter("exportDate"));

            String[] selectedIndexes = request.getParameterValues("selectedIndex");
            String[] bookIDs = request.getParameterValues("bookID[]");
            String[] quantities = request.getParameterValues("quantity[]");
            String[] prices = request.getParameterValues("price[]");
            String[] reasons = request.getParameterValues("reason[]");

            int staffID = new StaffDAO().getStaffIDByUsername(staffUsername);
            if (staffID == -1) throw new Exception("Invalid staff");

            ExportStockDTO export = new ExportStockDTO(0, customerName, exportDate, staffID, "");
            int exportID = exportDAO.insertExportStock(export);

            if (selectedIndexes != null) {
                for (String idxStr : selectedIndexes) {
                    int i = Integer.parseInt(idxStr);

                    int bookID = Integer.parseInt(bookIDs[i]);
                    int quantity = Integer.parseInt(quantities[i]);
                    double price = Double.parseDouble(prices[i]);
                    String reason = reasons[i];

                    ExportStockDetailDTO detail = new ExportStockDetailDTO(0, exportID, bookID, quantity, price, reason);
                    exportDAO.insertExportStockDetail(detail);

                    bookDAO.updateBook(bookID, -quantity); // reduce stock
                }
            }

            response.sendRedirect(request.getContextPath() + "/admin/stock/export/list");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
