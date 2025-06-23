package dao;

import dto.AccountDTO;
import util.DBContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    // === THÊM TÀI KHOẢN MỚI ===
    public boolean addAccount(AccountDTO acc) {
        String sql = "INSERT INTO Account (username, password, firstName, lastName, email, phone, role, address, accStatus, dob, sex, code) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, acc.getUsername());
            ps.setString(2, acc.getPassword());
            ps.setString(3, acc.getFirstName());
            ps.setString(4, acc.getLastName());
            ps.setString(5, acc.getEmail());
            ps.setString(6, acc.getPhone());
            ps.setInt(7, acc.getRole());
            ps.setString(8, acc.getAddress());
            ps.setInt(9, acc.getAccStatus());
            ps.setDate(10, acc.getDob());
            ps.setInt(11, acc.getSex());
            ps.setString(12, acc.getCode());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // === ĐĂNG NHẬP ===
    public AccountDTO login(String username, String password) {
        String sql = "SELECT * FROM Account WHERE username = ? AND password = ? AND accStatus = 1";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return extractAccount(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // === TÌM THEO EMAIL ===
    public AccountDTO findByEmail(String email) {
        String sql = "SELECT * FROM Account WHERE email = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return extractAccount(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public AccountDTO getAccountByUsername(String username) {
        String sql = "SELECT * FROM Account WHERE username = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return extractAccount(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // === CẬP NHẬT THÔNG TIN CÁ NHÂN ===
    public boolean updateProfile(AccountDTO acc) {
        String sql = "UPDATE Account SET firstName=?, lastName=?, email=?, phone=?, address=? WHERE username=?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, acc.getFirstName());
            ps.setString(2, acc.getLastName());
            ps.setString(3, acc.getEmail());
            ps.setString(4, acc.getPhone());
            ps.setString(5, acc.getAddress());
            ps.setString(6, acc.getUsername());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // === CẬP NHẬT MẬT KHẨU ===
    public boolean updatePassword(String email, String newPassword) {
        String sql = "UPDATE Account SET password = ? WHERE email = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newPassword);
            ps.setString(2, email);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePasswordByUsername(String username, String newPassword) {
        String sql = "UPDATE Account SET password = ? WHERE username = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newPassword);
            ps.setString(2, username);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePasswordByOld(String username, String oldPass, String newPass) {
        String sql = "UPDATE Account SET password = ? WHERE username = ? AND password = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newPass);
            ps.setString(2, username);
            ps.setString(3, oldPass);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // === CẬP NHẬT TỪ ADMIN ===
    public boolean updateAccountByAdmin(AccountDTO acc) {
        String sql = "UPDATE Account SET firstName=?, lastName=?, email=?, phone=?, address=?, role=?, sex=?, dob=? WHERE username=?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, acc.getFirstName());
            ps.setString(2, acc.getLastName());
            ps.setString(3, acc.getEmail());
            ps.setString(4, acc.getPhone());
            ps.setString(5, acc.getAddress());
            ps.setInt(6, acc.getRole());
            ps.setInt(7, acc.getSex());
            ps.setDate(8, acc.getDob());
            ps.setString(9, acc.getUsername());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // === XÓA ACCOUNT ===
    public boolean deleteAccount(String username) {
        String sql = "DELETE FROM Account WHERE username = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // === LẤY TẤT CẢ ACCOUNT ===
    public List<AccountDTO> getAllAccounts() {
        List<AccountDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Account";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(extractAccount(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // === TÌM KIẾM THEO KEYWORD ===
    public List<AccountDTO> searchAccounts(String keyword) {
        List<AccountDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Account WHERE LOWER(username) LIKE ? OR LOWER(CONCAT(firstName, ' ', lastName)) LIKE ? OR LOWER(email) LIKE ? OR phone LIKE ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            String search = "%" + keyword.toLowerCase() + "%";
            for (int i = 1; i <= 4; i++) {
                ps.setString(i, search);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(extractAccount(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // === LƯU MÃ OTP ===
    public void saveOTPCode(String username, String otp) {
        String sql = "UPDATE Account SET code = ? WHERE username = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, otp);
            ps.setString(2, username);
            ps.executeUpdate();
            System.out.println("✅ OTP đã lưu cho " + username);

        } catch (Exception e) {
            System.err.println("❌ Lỗi lưu OTP: " + e.getMessage());
        }
    }

    // === XÁC THỰC OTP ===
    public boolean verifyOTP(String username, String otp) {
        String sql = "SELECT 1 FROM Account WHERE username = ? AND code = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, otp);
            ResultSet rs = ps.executeQuery();

            boolean matched = rs.next();
            System.out.println("🔍 OTP check for " + username + ": " + matched);
            return matched;

        } catch (Exception e) {
            System.err.println("❌ Lỗi verify OTP: " + e.getMessage());
        }
        return false;
    }

    // === LẤY OTP HIỆN TẠI ===
    public String getOTPCode(String username) {
        String sql = "SELECT code FROM Account WHERE username = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("code");
            }

        } catch (Exception e) {
            System.err.println("❌ Lỗi get OTP: " + e.getMessage());
        }
        return null;
    }

    // === MAP RESULTSET → DTO ===
    private AccountDTO extractAccount(ResultSet rs) throws SQLException {
        return new AccountDTO(
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getDate("dob"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getInt("role"),
                rs.getString("address"),
                rs.getInt("sex"),
                rs.getInt("accStatus"),
                rs.getString("code")
        );
    }
}
