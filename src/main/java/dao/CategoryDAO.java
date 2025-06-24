/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import util.DBContext;
import dto.CategoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ngtua
 */
public class CategoryDAO {

    // Lấy tất cả danh mục
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Category";

        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CategoryDTO c = new CategoryDTO(
                        rs.getInt("catID"), // ✅ Đúng tên cột
                        rs.getString("catName"),
                        rs.getString("catDescription")
                );
                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy danh mục theo ID
    public CategoryDTO getCategoryById(int id) {
        String sql = "SELECT * FROM Category WHERE catID = ?";

        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new CategoryDTO(
                        rs.getInt("catID"),
                        rs.getString("catName"),
                        rs.getString("catDescription")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Thêm danh mục mới
    public boolean addCategory(CategoryDTO category) {
        String sql = "INSERT INTO Category (catName, catDescription, catStatus) VALUES (?, ?, ?)";

        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getCategoryDescription());
            ps.setInt(3, 1); // ✅ Trạng thái hoạt động

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Sửa danh mục
    public boolean updateCategory(CategoryDTO category) {
        String sql = "UPDATE Category SET catName = ?, catDescription = ? WHERE catID = ?";

        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getCategoryDescription());
            ps.setInt(3, category.getCategoryID());
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xoá danh mục
    public void deleteCategory(int id) {
        String sql = "DELETE FROM Category WHERE catID = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tìm kiếm danh mục theo tên hoặc mô tả
    public List<CategoryDTO> searchCategories(String keyword) {
    List<CategoryDTO> list = new ArrayList<>();
    String sql = "SELECT * FROM Category WHERE catName LIKE ? OR catDescription LIKE ?";

    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        String searchPattern = "%" + keyword + "%";
        ps.setString(1, searchPattern);
        ps.setString(2, searchPattern);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            CategoryDTO c = new CategoryDTO(
                    rs.getInt("catID"),
                    rs.getString("catName"),
                    rs.getString("catDescription")
            );
            list.add(c);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}


    public List<CategoryDTO> getAllCategoriesWithBooks() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

