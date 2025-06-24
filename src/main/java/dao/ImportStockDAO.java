package dao;

import dto.ImportStockDTO;
import dto.ImportStockDetailDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.DBContext.getConnection;

public class ImportStockDAO {

    // Thêm phiếu nhập mới và trả về ID tự sinh
    public int insertImportStock(ImportStockDTO stock) throws SQLException {
        String sql = "INSERT INTO ImportStock (supID, importDate, ISStatus, staffID) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, stock.getSupID());
            ps.setDate(2, stock.getImportDate());
            ps.setInt(3, stock.getISStatus());
            ps.setInt(4, stock.getStaffID());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // trả về ISID
            }
        }
        return -1;
    }

    // Thêm chi tiết nhập kho (sách, số lượng, giá nhập)
    public void insertImportStockDetail(ImportStockDetailDTO detail) throws SQLException {
        String sql = "INSERT INTO ImportStockDetail (bookID, ISID, ISDQuantity, importPrice) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, detail.getBookID());
            ps.setInt(2, detail.getISID());
            ps.setInt(3, detail.getISDQuantity());
            ps.setDouble(4, detail.getImportPrice());
            ps.executeUpdate();
        }
    }

    // Cập nhật tồn kho sách sau khi nhập
    public void updateBookStock(int bookID, int quantity) throws SQLException {
        String sql = "UPDATE Book SET bookQuantity = bookQuantity + ? WHERE bookID = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, bookID);
            ps.executeUpdate();
        }
    }

    // Lấy danh sách tất cả phiếu nhập kèm tổng giá trị
    public List<ImportStockDTO> getAllImportStocks() {
        List<ImportStockDTO> list = new ArrayList<>();
        String sql = "SELECT s.ISID, s.supID, s.staffID, s.importDate, " +
                     "sp.supName, st.username AS staffName, " +
                     "ISNULL(SUM(d.ISDQuantity * d.importPrice), 0) AS totalPrice " +
                     "FROM ImportStock s " +
                     "JOIN Supplier sp ON s.supID = sp.supID " +
                     "JOIN Staff st ON s.staffID = st.staffID " +
                     "LEFT JOIN ImportStockDetail d ON s.ISID = d.ISID " +
                     "GROUP BY s.ISID, s.supID, s.staffID, s.importDate, sp.supName, st.username";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ImportStockDTO dto = new ImportStockDTO(
                        rs.getInt("ISID"),
                        rs.getInt("supID"),
                        rs.getDate("importDate"),
                        rs.getInt("staffID"),
                        1,
                        rs.getString("supName"),
                        rs.getString("staffName"),
                        rs.getDouble("totalPrice")
                );
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy chi tiết phiếu nhập theo ISID
    public List<ImportStockDetailDTO> getImportStockDetailsByISID(int isid) {
        List<ImportStockDetailDTO> list = new ArrayList<>();
        String sql = "SELECT d.ISDID, d.bookID, d.ISID, d.ISDQuantity, d.importPrice, b.bookTitle " +
                     "FROM ImportStockDetail d " +
                     "JOIN Book b ON d.bookID = b.bookID " +
                     "WHERE d.ISID = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, isid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ImportStockDetailDTO dto = new ImportStockDetailDTO(
                        rs.getInt("ISDID"),
                        rs.getInt("bookID"),
                        rs.getInt("ISID"),
                        rs.getInt("ISDQuantity"),
                        rs.getDouble("importPrice")
                );
                dto.setBookTitle(rs.getString("bookTitle")); // tên sách để hiển thị
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
