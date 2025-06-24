package controller.stock;

import dao.ImportStockDAO;
import dto.ImportStockDetailDTO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/ImportStockDetailServlet")
public class ImportStockDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int isid = Integer.parseInt(request.getParameter("isid"));
            List<ImportStockDetailDTO> details = new ImportStockDAO().getImportStockDetailsByISID(isid);
            request.setAttribute("details", details);
            request.setAttribute("isid", isid);
            request.getRequestDispatcher("/WEB-INF/view/admin/stock/importStockDetail.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}