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

@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/forgot-password"})
public class ForgotPasswordServlet extends HttpServlet {

    /**
     * Processes both GET and POST requests for password reset by email.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        if ("GET".equalsIgnoreCase(request.getMethod())) {
            // Show forgot password form
            request.getRequestDispatcher("/WEB-INF/view/account/forgotPassword.jsp").forward(request, response);
            return;
        }

        // Handle POST: verify email and redirect to reset
        String email = request.getParameter("email");
        AccountDAO dao = new AccountDAO();
        AccountDTO acc = dao.findByEmail(email);

        if (acc != null) {
            HttpSession session = request.getSession();
            session.setAttribute("resetEmail", email);
            // Simulate sending OTP (omitted)
            response.sendRedirect("view/account/resetPassword.jsp");
        } else {
            request.setAttribute("error", "Email not found!");
            request.getRequestDispatcher("/WEB-INF/view/account/forgotPassword.jsp").forward(request, response);
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
        return "Handles forgot password functionality by verifying email.";
    }
    // </editor-fold>
}
