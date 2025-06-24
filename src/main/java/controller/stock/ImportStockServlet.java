//package controller.stock;
//
//import dao.BookDAO;
//import dao.ExportStockDAO;
//import dao.ImportStockDAO;
//import dao.StaffDAO;
//import dao.SupplierDAO;
//import dto.ExportStockDTO;
//import dto.ImportStockDTO;
//import dto.ImportStockDetailDTO;
//
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.*;
//import jakarta.servlet.*;
//
//import java.io.IOException;
//import java.sql.Date;
//import java.util.List;
//
//@WebServlet("/admin/stock/list")
//public class ImportStockServlet extends HttpServlet {
//    private ImportStockDAO importDAO;
//    private ExportStockDAO exportDAO;
//
//    @Override
//    public void init() throws ServletException {
//        importDAO = new ImportStockDAO();
//        exportDAO = new ExportStockDAO();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        List<ImportStockDTO> importList = importDAO.getAllImportStocks();
//        List<ExportStockDTO> exportList = exportDAO.getAllExportStocks();
//
//        request.setAttribute("list", importList);
//        request.setAttribute("exportList", exportList);
//
//        request.getRequestDispatcher("/WEB-INF/view/admin/stock/importStockList.jsp").forward(request, response);
//    }
//
//
//
//@Override
//protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//    try {
//        String supName = request.getParameter("supplierName");
//        String staffUsername = request.getParameter("staffName");
//        Date importDate = Date.valueOf(request.getParameter("importDate"));
//
//        String[] bookIDs = request.getParameterValues("bookID[]");
//        String[] quantities = request.getParameterValues("quantity[]");
//        String[] prices = request.getParameterValues("price[]");
//
//        int supID = new SupplierDAO().getSupplierIDByName(supName);
//        int staffID = new StaffDAO().getStaffIDByUsername(staffUsername);
//
//        if (supID == -1 || staffID == -1) throw new Exception("Invalid supplier or staff");
//
//        ImportStockDTO stock = new ImportStockDTO(0, supID, importDate, staffID, 1);
//        int ISID = importDAO.insertImportStock(stock);
//
//        for (int i = 0; i < bookIDs.length; i++) {
//            int bookID = Integer.parseInt(bookIDs[i]);
//            int quantity = Integer.parseInt(quantities[i]);
//            double price = Double.parseDouble(prices[i]);
//
//            importDAO.insertImportStockDetail(new ImportStockDetailDTO(0, bookID, ISID, quantity, price));
//            importDAO.updateBookStock(bookID, quantity);
//        }
//
//        response.sendRedirect(request.getContextPath() + "/admin/stock/list?action=list");
//    } catch (Exception e) {
//        e.printStackTrace();
//        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//    }
//}
//}
