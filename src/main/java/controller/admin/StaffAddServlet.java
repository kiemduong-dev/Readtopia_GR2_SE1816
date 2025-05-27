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

@WebServlet(name = "StaffAddServlet", urlPatterns = {"/admin/staff/add"})
public class StaffAddServlet extends HttpServlet {

    /**
     * Processes both GET and POST requests to add a new staff account.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        if ("GET".equalsIgnoreCase(request.getMethod())) {
            // Show staff add form
            request.getRequestDispatcher("/WEB-INF/view/admin/staff/add.jsp").forward(request, response);
            return;
        }

        // Handle POST: process adding staff
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        AccountDTO staff = new AccountDTO(username, password, fullName, email, phone, "staff", address);
        AccountDAO dao = new AccountDAO();
        boolean success = dao.addAccount(staff);

        if (success) {
            response.sendRedirect("list");
        } else {
            request.setAttribute("error", "Failed to add staff (username may already exist).");
            request.setAttribute("staff", staff);
            request.getRequestDispatcher("/WEB-INF/view/admin/staff/add.jsp").forward(request, response);
        }
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
        return "Handles admin request to add a new staff account.";
    }
    // </editor-fold>
}
