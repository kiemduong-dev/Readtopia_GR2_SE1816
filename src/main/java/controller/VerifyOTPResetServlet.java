package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 * Servlet xử lý xác thực OTP trong luồng quên mật khẩu.
 */
@WebServlet(name = "VerifyOTPResetServlet", urlPatterns = {"/verify-otp-reset"})
public class VerifyOTPResetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String enteredOtp = request.getParameter("otp");
        HttpSession session = request.getSession(false); // Không tạo mới

        // ❌ KHẮC PHỤC lỗi: session thiếu `resetUser` hoặc `otp` sẽ redirect sai
        if (session == null
                || session.getAttribute("otp") == null
                || session.getAttribute("resetUser") == null
                || session.getAttribute("otpPurpose") == null
                || !"reset".equals(session.getAttribute("otpPurpose").toString())) {
            // Trường hợp không hợp lệ → quay lại form quên mật khẩu
            response.sendRedirect(request.getContextPath() + "/forgot-password");
            return;
        }

        String expectedOtp = (String) session.getAttribute("otp");

        if (enteredOtp != null && enteredOtp.equals(expectedOtp)) {
            // ✅ OTP đúng → dọn session và chuyển sang reset password
            session.removeAttribute("otp");
            session.removeAttribute("otpPurpose");

            // 🔑 CẦN THIẾT: đảm bảo servlet ResetPasswordServlet có thể truy cập username
            session.setAttribute("resetUsername", session.getAttribute("resetUser"));
            session.removeAttribute("resetUser");

            response.sendRedirect(request.getContextPath() + "/reset-password");
        } else {
            // ❌ OTP sai
            request.setAttribute("error", "Invalid OTP code. Please try again.");
            request.getRequestDispatcher("/WEB-INF/view/account/verifyOTP.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // ✅ Forward đúng sang form nhập OTP
        request.getRequestDispatcher("/WEB-INF/view/account/verifyOTP.jsp").forward(request, response);
    }
}
