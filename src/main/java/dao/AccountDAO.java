/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.AccountDTO;
import util.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    public boolean register(AccountDTO acc) {
        String sql = "INSERT INTO Account (username, password, fullname, email, phone, role, address, accStatus) VALUES (?, ?, ?, ?, ?, ?, ?, 1)";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, acc.getUsername());
            ps.setString(2, acc.getPassword());
            ps.setString(3, acc.getFullName());
            ps.setString(4, acc.getEmail());
            ps.setString(5, acc.getPhone());
            ps.setString(6, acc.getRole());
            ps.setString(7, acc.getAddress());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public AccountDTO login(String username, String password) {
        String sql = "SELECT * FROM Account WHERE username = ? AND password = ? AND accStatus = 1";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new AccountDTO(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("role"),
                        rs.getString("address")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public AccountDTO findByEmail(String email) {
        String sql = "SELECT * FROM Account WHERE email = ?";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new AccountDTO(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("role"),
                        rs.getString("address")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePassword(String email, String newPassword) {
        String sql = "UPDATE Account SET password = ? WHERE email = ?";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateProfile(AccountDTO acc) {
        String sql = "UPDATE Account SET fullname = ?, email = ?, phone = ?, address = ? WHERE username = ?";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, acc.getFullName());
            ps.setString(2, acc.getEmail());
            ps.setString(3, acc.getPhone());
            ps.setString(4, acc.getAddress());
            ps.setString(5, acc.getUsername());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePasswordByOld(String username, String oldPass, String newPass) {
        String sql = "UPDATE Account SET password = ? WHERE username = ? AND password = ?";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newPass);
            ps.setString(2, username);
            ps.setString(3, oldPass);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<AccountDTO> getAllAccounts() {
        List<AccountDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Account";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new AccountDTO(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("role"),
                        rs.getString("address")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public AccountDTO getAccountByUsername(String username) {
        String sql = "SELECT * FROM Account WHERE username = ?";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new AccountDTO(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("role"),
                        rs.getString("address")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateAccountByAdmin(AccountDTO acc) {
        String sql = "UPDATE Account SET fullname = ?, email = ?, phone = ?, address = ?, role = ? WHERE username = ?";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, acc.getFullName());
            ps.setString(2, acc.getEmail());
            ps.setString(3, acc.getPhone());
            ps.setString(4, acc.getAddress());
            ps.setString(5, acc.getRole());
            ps.setString(6, acc.getUsername());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addAccount(AccountDTO acc) {
        String sql = "INSERT INTO Account (username, password, fullname, email, phone, role, address, accStatus) VALUES (?, ?, ?, ?, ?, ?, ?, 1)";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, acc.getUsername());
            ps.setString(2, acc.getPassword());
            ps.setString(3, acc.getFullName());
            ps.setString(4, acc.getEmail());
            ps.setString(5, acc.getPhone());
            ps.setString(6, acc.getRole());
            ps.setString(7, acc.getAddress());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteAccount(String username) {
        String sql = "DELETE FROM Account WHERE username = ?";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<AccountDTO> searchAccounts(String keyword) {
        List<AccountDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Account WHERE "
                + "LOWER(username) LIKE ? OR LOWER(fullname) LIKE ? OR LOWER(email) LIKE ? OR phone LIKE ?";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            String search = "%" + keyword.toLowerCase() + "%";
            for (int i = 1; i <= 4; i++) {
                ps.setString(i, search);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new AccountDTO(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("role"),
                        rs.getString("address")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<AccountDTO> getAllStaffs() {
        List<AccountDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Account WHERE role = 'staff'";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new AccountDTO(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("role"),
                        rs.getString("address")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<AccountDTO> searchStaffs(String keyword) {
        List<AccountDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Account WHERE role = 'staff' AND "
                + "(LOWER(username) LIKE ? OR LOWER(fullname) LIKE ? OR LOWER(email) LIKE ? OR phone LIKE ?)";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            String kw = "%" + keyword.toLowerCase() + "%";
            for (int i = 1; i <= 4; i++) {
                ps.setString(i, kw);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new AccountDTO(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("role"),
                        rs.getString("address")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
