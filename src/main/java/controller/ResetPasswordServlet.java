package controller;

import dao.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "ResetPasswordServlet", urlPatterns = {"/reset-password"})
public class ResetPasswordServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("resetUsername") : null;

        if (username == null) {
            response.sendRedirect("forgot-password");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Confirmed password does not match.");
            request.getRequestDispatcher("/WEB-INF/view/account/resetPassword.jsp").forward(request, response);
            return;
        }

        AccountDAO dao = new AccountDAO();
        boolean updated = dao.updatePasswordByUsername(username, newPassword);

        if (updated) {
            session.removeAttribute("resetUsername");

            request.setAttribute("success", "Password has been reset successfully.");
            request.getRequestDispatcher("/WEB-INF/view/account/login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Failed to reset password. Please try again.");
            request.getRequestDispatcher("/WEB-INF/view/account/resetPassword.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("forgot-password");
    }

    @Override
    public String getServletInfo() {
        return "Handles password reset after OTP verification";
    }
}
