package controller;

import dao.AccountDAO;
import dto.AccountDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import util.MailUtil;

import java.io.IOException;
import java.util.Random;

/**
 * ForgotPasswordServlet
 *
 * This servlet handles the "Forgot Password" process: - Verifies user by
 * username and email - Generates a 6-digit OTP - Sends OTP to user's email -
 * Stores OTP in both session and database
 */
@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/forgot-password"})
public class ForgotPasswordServlet extends HttpServlet {

    /**
     * Display forgot password page on GET
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/account/forgotPassword.jsp").forward(request, response);
    }

    /**
     * Handle forgot password form submission on POST
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Step 1: Get form values
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        AccountDAO dao = new AccountDAO();
        AccountDTO account = dao.getAccountByUsername(username);

        // Step 2: Validate account and email
        if (account != null && account.getEmail().equalsIgnoreCase(email)) {
            // Step 3: Generate OTP
            String otp = String.format("%06d", new Random().nextInt(1_000_000));

            // Step 4: Save OTP to DB
            dao.saveOTPCode(username, otp);

            try {
                // Step 5: Send OTP
                MailUtil.sendOTP(email, otp);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Failed to send OTP to your email. Please try again.");
                request.getRequestDispatcher("/WEB-INF/view/account/forgotPassword.jsp").forward(request, response);
                return;
            }

            // Step 6: Store in session
            HttpSession session = request.getSession();
            session.setAttribute("otp", otp);
            session.setAttribute("resetUser", username);
            session.setAttribute("resetEmail", email);
            session.setAttribute("otpPurpose", "reset");

            // ✅ Step 7: Redirect to correct OTP input page for password reset
            response.sendRedirect(request.getContextPath() + "/verify-otp-reset");

        } else {
            // Invalid case
            request.setAttribute("error", "Username or email is incorrect.");
            request.getRequestDispatcher("/WEB-INF/view/account/forgotPassword.jsp").forward(request, response);
        }
    }
}
