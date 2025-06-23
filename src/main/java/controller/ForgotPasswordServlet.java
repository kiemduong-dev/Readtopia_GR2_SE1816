package controller;

import dao.AccountDAO;
import dto.AccountDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import util.MailUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * ForgotPasswordServlet
 *
 * Handles: 1. User submits username and email. 2. System verifies info,
 * generates OTP, emails OTP. 3. Stores OTP + user info in session & DB.
 */
@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/forgot-password"})
public class ForgotPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/account/forgotPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");

        AccountDAO dao = new AccountDAO();
        AccountDTO account = dao.getAccountByUsername(username);

        if (account != null && account.getEmail().equalsIgnoreCase(email)) {
            String otp = String.format("%06d", new Random().nextInt(1_000_000));
            dao.saveOTPCode(username, otp);

            try {
                MailUtil.sendOTP(email, otp);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "⚠️ Gửi OTP thất bại. Vui lòng thử lại.");
                request.getRequestDispatcher("/WEB-INF/view/account/forgotPassword.jsp").forward(request, response);
                return;
            }

            // ✅ KHÔNG dùng session.getSession(false), luôn tạo mới (true)
            HttpSession session = request.getSession(true);
            session.setAttribute("otp", otp);
            session.setAttribute("resetUser", username);
            session.setAttribute("resetEmail", email);
            session.setAttribute("otpPurpose", "reset");

            System.out.println("✅ OTP gửi: " + otp + " cho user: " + username);
            response.sendRedirect(request.getContextPath() + "/verify-otp-reset");
        } else {
            request.setAttribute("error", "⚠️ Username hoặc email không đúng.");
            request.getRequestDispatcher("/WEB-INF/view/account/forgotPassword.jsp").forward(request, response);
        }
    }
}
