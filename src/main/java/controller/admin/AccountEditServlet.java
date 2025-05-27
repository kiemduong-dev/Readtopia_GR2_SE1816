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

@WebServlet(name = "AccountEditServlet", urlPatterns = {"/admin/account/edit"})
public class AccountEditServlet extends HttpServlet {

    /**
     * Processes both GET and POST requests for editing account by admin.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String method = request.getMethod();
        AccountDAO dao = new AccountDAO();

        if ("GET".equalsIgnoreCase(method)) {
            String username = request.getParameter("username");
            AccountDTO acc = dao.getAccountByUsername(username);

            if (acc == null) {
                response.sendRedirect("list");
                return;
            }

            request.setAttribute("account", acc);
            request.getRequestDispatcher("/WEB-INF/view/admin/account/edit.jsp").forward(request, response);
            return;
        }

        // Handle POST: update account
        String username = request.getParameter("username");
        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String role = request.getParameter("role");

        AccountDTO acc = new AccountDTO(username, null, fullName, email, phone, role, address);
        boolean success = dao.updateAccountByAdmin(acc);

        if (success) {
            response.sendRedirect("list");
        } else {
            request.setAttribute("error", "Failed to update account.");
            request.setAttribute("account", acc);
            request.getRequestDispatcher("/WEB-INF/view/admin/account/edit.jsp").forward(request, response);
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
        return "Handles admin account editing functionality.";
    }
    // </editor-fold>
}
