package controller.stock;

import dao.ExportStockDAO;
import dto.ExportStockDTO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/stock/export/exportStock")
public class ExportStockServlet extends HttpServlet {
    private ExportStockDAO exportDAO;

    @Override
    public void init() throws ServletException {
        exportDAO = new ExportStockDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ExportStockDTO> list = exportDAO.getAllExportStocks();
        request.setAttribute("exportList", list);
        request.getRequestDispatcher("/WEB-INF/view/admin/stock/exportStock.jsp").forward(request, response);
    }
}
