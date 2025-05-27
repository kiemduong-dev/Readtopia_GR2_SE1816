/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "ResetPasswordServlet", urlPatterns = {"/reset-password"})
public class ResetPasswordServlet extends HttpServlet {

    /**
     * Processes POST request for resetting password using email stored in session.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("resetEmail");

        if (email == null) {
            response.sendRedirect("forgot-password");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Confirm password does not match!");
            request.getRequestDispatcher("/WEB-INF/view/account/resetPassword.jsp").forward(request, response);
            return;
        }

        AccountDAO dao = new AccountDAO();
        boolean success = dao.updatePassword(email, newPassword);

        if (success) {
            session.removeAttribute("resetEmail");
            response.sendRedirect("login");
        } else {
            request.setAttribute("error", "Failed to update password.");
            request.getRequestDispatcher("/WEB-INF/view/account/resetPassword.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Optional: redirect to forgot password if accessed directly
        response.sendRedirect("forgot-password");
    }

    /**
     * Returns a short description of this servlet.
     */
    @Override
    public String getServletInfo() {
        return "Handles password reset using email verification.";
    }
    // </editor-fold>
}
