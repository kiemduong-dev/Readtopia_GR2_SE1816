/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.AccountDAO;
import dto.AccountDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "StaffDeleteServlet", urlPatterns = {"/admin/staff/delete"})
public class StaffDeleteServlet extends HttpServlet {

    /**
     * Processes GET and POST requests to delete a staff account.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        AccountDAO dao = new AccountDAO();

        // Validate staff before deletion
        if (username != null && !username.trim().isEmpty()) {
            AccountDTO staff = dao.getAccountByUsername(username);
            if (staff != null && "staff".equalsIgnoreCase(staff.getRole())) {
                dao.deleteAccount(username);
            }
        }

        response.sendRedirect("list");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of this servlet.
     */
    @Override
    public String getServletInfo() {
        return "Handles deletion of staff accounts by admin.";
    }
    // </editor-fold>
}
