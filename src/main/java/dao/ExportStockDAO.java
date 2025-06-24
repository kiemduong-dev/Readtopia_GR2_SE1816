//package dao;
//
//import dto.ExportStockDTO;
//import dto.ExportStockDetailDTO;
//import util.DBContext;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ExportStockDAO {
//
//    public int insertExportStock(ExportStockDTO export) throws SQLException {
//        String sql = "INSERT INTO ExportStock (customerName, exportDate, staffID, reason) VALUES (?, ?, ?, ?)";
//        try (Connection conn = DBContext.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//
//            ps.setString(1, export.getCustomerName());
//            ps.setDate(2, export.getExportDate());
//            ps.setInt(3, export.getStaffID());
//            ps.setString(4, export.getReason());
//
//            ps.executeUpdate();
//            ResultSet rs = ps.getGeneratedKeys();
//            return rs.next() ? rs.getInt(1) : -1;
//        }
//    }
//
//    public void insertExportStockDetail(ExportStockDetailDTO detail) throws SQLException {
//        String sql = "INSERT INTO ExportStockDetail (exportID, bookID, quantity, price, reason) VALUES (?, ?, ?, ?, ?)";
//        try (Connection conn = DBContext.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, detail.getExportID());
//            ps.setInt(2, detail.getBookID());
//            ps.setInt(3, detail.getQuantity());
//            ps.setDouble(4, detail.getPrice());
//            ps.setString(5, detail.getReason());
//
//            ps.executeUpdate();
//        }
//    }
//
//    public List<ExportStockDetailDTO> getExportDetailsByExportID(int exportID) {
//        List<ExportStockDetailDTO> list = new ArrayList<>();
//        String sql = "SELECT * FROM ExportStockDetail WHERE exportID = ?";
//
//        try (Connection conn = DBContext.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, exportID);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new ExportStockDetailDTO(
//                        rs.getInt("exportDetailID"),
//                        rs.getInt("exportID"),
//                        rs.getInt("bookID"),
//                        rs.getInt("quantity"),
//                        rs.getDouble("price"),
//                        rs.getString("reason")
//                ));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return list;
//    }
//
//    public List<ExportStockDTO> getAllExportStocks() {
//        List<ExportStockDTO> list = new ArrayList<>();
//        String sql = "SELECT e.exportID, e.customerName, e.exportDate, s.username AS staffName, " +
//                     "SUM(d.price * d.quantity) AS total " +
//                     "FROM ExportStock e " +
//                     "JOIN Staff s ON e.staffID = s.staffID " +
//                     "JOIN ExportStockDetail d ON e.exportID = d.exportID " +
//                     "GROUP BY e.exportID, e.customerName, e.exportDate, s.username";
//
//        try (Connection conn = DBContext.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql);
//             ResultSet rs = ps.executeQuery()) {
//
//            while (rs.next()) {
//                list.add(new ExportStockDTO(
//                        rs.getInt("exportID"),
//                        rs.getString("customerName"),
//                        rs.getDate("exportDate"),
//                        rs.getString("staffName"),
//                        rs.getDouble("total")
//                ));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return list;
//    }
//}
