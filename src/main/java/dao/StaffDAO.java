/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ADMIN
 */


import dto.StaffDTO;
import util.DBContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DBContext;

/**
 * Data Access Object (DAO) class that manages all database interactions
 * related to the Staff entity, including CRUD operations and searches.
 */
public class StaffDAO {

    /**
     * Retrieves all staff records by joining Staff and Account tables.
     *
     * @return a list of StaffDTO objects representing all staff in the system
     */
    public List<StaffDTO> getAllStaffs() {
        List<StaffDTO> list = new ArrayList<>();
        String sql = "SELECT s.staffID, a.* FROM Staff s JOIN Account a ON s.username = a.username";

        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Iterate through each row and map it to StaffDTO
            while (rs.next()) {
                list.add(extractStaff(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Searches for staff using a keyword against username, first name, or last name.
     *
     * @param keyword input text to search
     * @return list of matched StaffDTOs
     */
    public List<StaffDTO> searchStaffs(String keyword) {
        List<StaffDTO> list = new ArrayList<>();
        String sql = "SELECT s.staffID, a.* FROM Staff s JOIN Account a ON s.username = a.username " +
                     "WHERE a.username LIKE ? OR a.firstName LIKE ? OR a.lastName LIKE ?";

        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String kw = "%" + keyword + "%";
            ps.setString(1, kw);
            ps.setString(2, kw);
            ps.setString(3, kw);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(extractStaff(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Retrieves a single staff by their staff ID.
     *
     * @param staffID the staff ID to query
     * @return a StaffDTO object if found, or null otherwise
     */
    public StaffDTO getStaffByID(int staffID) {
        String sql = "SELECT s.staffID, a.* FROM Staff s JOIN Account a ON s.username = a.username WHERE s.staffID = ?";

        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, staffID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return extractStaff(rs);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Adds a new staff by inserting data into both Account and Staff tables.
     * Performs the operation in a transaction.
     *
     * @param staff the StaffDTO object to be inserted
     * @return true if the operation is successful, false otherwise
     */
    public boolean addStaff(StaffDTO staff) {
        String insertAccount = "INSERT INTO Account (username, password, firstName, lastName, dob, email, phone, role, sex, address, accStatus, code) " +
                               "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String insertStaff = "INSERT INTO Staff (username) VALUES (?)";

        try (Connection conn = new DBContext().getConnection()) {
            conn.setAutoCommit(false); // Begin transaction

            try (PreparedStatement ps1 = conn.prepareStatement(insertAccount);
                 PreparedStatement ps2 = conn.prepareStatement(insertStaff)) {

                // Populate Account table
                ps1.setString(1, staff.getUsername());
                ps1.setString(2, staff.getPassword());
                ps1.setString(3, staff.getFirstName());
                ps1.setString(4, staff.getLastName());
                ps1.setDate(5, staff.getDob());
                ps1.setString(6, staff.getEmail());
                ps1.setString(7, staff.getPhone());
                ps1.setInt(8, staff.getRole());
                ps1.setInt(9, staff.getSex());
                ps1.setString(10, staff.getAddress());
                ps1.setInt(11, staff.getAccStatus());
                ps1.setString(12, staff.getCode());
                ps1.executeUpdate();

                // Populate Staff table
                ps2.setString(1, staff.getUsername());
                ps2.executeUpdate();

                conn.commit();
                return true;

            } catch (Exception e) {
                conn.rollback(); // Rollback if any step fails
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Updates an existing staff's account information.
     *
     * @param staff updated StaffDTO object
     * @return true if update is successful, false otherwise
     */
    public boolean updateStaff(StaffDTO staff) {
        String sql = "UPDATE Account SET firstName=?, lastName=?, dob=?, email=?, phone=?, role=?, sex=?, address=?, accStatus=?, code=? WHERE username=?";

        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, staff.getFirstName());
            ps.setString(2, staff.getLastName());
            ps.setDate(3, staff.getDob());
            ps.setString(4, staff.getEmail());
            ps.setString(5, staff.getPhone());
            ps.setInt(6, staff.getRole());
            ps.setInt(7, staff.getSex());
            ps.setString(8, staff.getAddress());
            ps.setInt(9, staff.getAccStatus());
            ps.setString(10, staff.getCode());
            ps.setString(11, staff.getUsername());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Deletes a staff from both Staff and Account tables using staff ID.
     * This operation is transactional.
     *
     * @param staffID ID of the staff to delete
     * @return true if deletion is successful, false otherwise
     */
    public boolean deleteStaffByID(int staffID) {
        String selectUsername = "SELECT username FROM Staff WHERE staffID = ?";
        String deleteStaff = "DELETE FROM Staff WHERE staffID = ?";
        String deleteAccount = "DELETE FROM Account WHERE username = ?";

        try (Connection conn = new DBContext().getConnection()) {
            conn.setAutoCommit(false);
            String username = null;

            try (PreparedStatement ps = conn.prepareStatement(selectUsername)) {
                ps.setInt(1, staffID);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        username = rs.getString("username");
                    } else {
                        return false; // Staff not found
                    }
                }
            }

            try (PreparedStatement ps1 = conn.prepareStatement(deleteStaff);
                 PreparedStatement ps2 = conn.prepareStatement(deleteAccount)) {

                ps1.setInt(1, staffID);
                ps1.executeUpdate();

                ps2.setString(1, username);
                ps2.executeUpdate();

                conn.commit();
                return true;

            } catch (Exception e) {
                conn.rollback();
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Helper method that extracts staff information from a ResultSet row
     * and maps it into a StaffDTO object.
     *
     * @param rs the result set containing a joined Staff + Account record
     * @return StaffDTO with mapped fields
     * @throws SQLException if column mapping fails
     */
    private StaffDTO extractStaff(ResultSet rs) throws SQLException {
        StaffDTO s = new StaffDTO();
        s.setStaffID(rs.getInt("staffID"));
        s.setUsername(rs.getString("username"));
        s.setPassword(rs.getString("password"));
        s.setFirstName(rs.getString("firstName"));
        s.setLastName(rs.getString("lastName"));
        s.setDob(rs.getDate("dob"));
        s.setEmail(rs.getString("email"));
        s.setPhone(rs.getString("phone"));
        s.setRole(rs.getInt("role"));
        s.setSex(rs.getInt("sex"));
        s.setAddress(rs.getString("address"));
        s.setAccStatus(rs.getInt("accStatus"));
        s.setCode(rs.getString("code"));
        return s;
    }
}
