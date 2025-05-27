/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dto.AccountDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/change-password"})
public class ChangePasswordServlet extends HttpServlet {

    /**
     * Processes both GET and POST requests for changing password.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        AccountDTO acc = (AccountDTO) session.getAttribute("account");

        if ("GET".equalsIgnoreCase(request.getMethod())) {
            // Display change password form
            request.getRequestDispatcher("/WEB-INF/view/account/changePassword.jsp").forward(request, response);
            return;
        }

        // Handle POST request: process password change
        String oldPass = request.getParameter("oldPassword");
        String newPass = request.getParameter("newPassword");
        String confirmPass = request.getParameter("confirmPassword");

        if (acc == null) {
            response.sendRedirect("login");
            return;
        }

        if (!newPass.equals(confirmPass)) {
            request.setAttribute("error", "Confirm password does not match!");
            request.getRequestDispatcher("/WEB-INF/view/account/changePassword.jsp").forward(request, response);
            return;
        }

        AccountDAO dao = new AccountDAO();
        boolean success = dao.updatePasswordByOld(acc.getUsername(), oldPass, newPass);

        if (success) {
            acc.setPassword(newPass);
            session.setAttribute("account", acc);
            response.sendRedirect("profile");
        } else {
            request.setAttribute("error", "Old password is incorrect!");
            request.getRequestDispatcher("/WEB-INF/view/account/changePassword.jsp").forward(request, response);
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
        return "Handles user password change functionality.";
    }
    // </editor-fold>
}
