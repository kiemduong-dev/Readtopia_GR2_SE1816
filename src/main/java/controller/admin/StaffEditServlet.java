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

@WebServlet(name = "StaffEditServlet", urlPatterns = {"/admin/staff/edit"})
public class StaffEditServlet extends HttpServlet {

    /**
     * Processes both GET and POST requests for editing a staff account.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String method = request.getMethod();
        AccountDAO dao = new AccountDAO();

        if ("GET".equalsIgnoreCase(method)) {
            // Show edit form with staff info
            String username = request.getParameter("username");
            AccountDTO staff = dao.getAccountByUsername(username);

            if (staff == null || !"staff".equalsIgnoreCase(staff.getRole())) {
                response.sendRedirect("list");
                return;
            }

            request.setAttribute("staff", staff);
            request.getRequestDispatcher("/WEB-INF/view/admin/staff/edit.jsp").forward(request, response);
            return;
        }

        // Handle POST: update staff info
        String username = request.getParameter("username");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        AccountDTO staff = new AccountDTO(username, null, fullname, email, phone, "staff", address);
        boolean success = dao.updateAccountByAdmin(staff);

        if (success) {
            response.sendRedirect("list");
        } else {
            request.setAttribute("error", "Failed to update staff account.");
            request.setAttribute("staff", staff);
            request.getRequestDispatcher("/WEB-INF/view/admin/staff/edit.jsp").forward(request, response);
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
        return "Handles editing of staff accounts by admin.";
    }
    // </editor-fold>
}
