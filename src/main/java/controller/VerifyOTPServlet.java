package controller;

import dao.AccountDAO;
import dto.AccountDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "VerifyOTPServlet", urlPatterns = {"/verify-otp"})
public class VerifyOTPServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String enteredOtp = request.getParameter("otp");
        HttpSession session = request.getSession(false);

        if (session == null
                || session.getAttribute("otp") == null
                || session.getAttribute("pendingAccount") == null
                || enteredOtp == null) {
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }

        String expectedOtp = (String) session.getAttribute("otp");
        AccountDTO pendingAccount = (AccountDTO) session.getAttribute("pendingAccount");

        if (enteredOtp.equals(expectedOtp)) {
            // OTP đúng → lưu vào DB
            AccountDAO dao = new AccountDAO();
            boolean success = dao.addAccount(pendingAccount);

            if (success) {
                session.removeAttribute("otp");
                session.removeAttribute("pendingAccount");

                session.setAttribute("success", "Account created successfully. Please log in.");
                response.sendRedirect(request.getContextPath() + "/login");
            } else {
                request.setAttribute("error", "Failed to create account. Please try again.");
                request.getRequestDispatcher("/WEB-INF/view/account/verifyOTP.jsp").forward(request, response);
            }

        } else {
            // OTP sai
            request.setAttribute("error", "Invalid OTP code. Please try again.");
            request.getRequestDispatcher("/WEB-INF/view/account/verifyOTP.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/register");
    }

    @Override
    public String getServletInfo() {
        return "Handles OTP verification during registration.";
    }
}
