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

        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("resetUsername") : null;

        if (username == null) {
            System.out.println("⚠️ Không có resetUsername trong session → về forgot-password");
            response.sendRedirect("forgot-password");
            return;
        }

        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (newPassword == null || confirmPassword == null || !newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Mật khẩu xác nhận không khớp hoặc bị trống.");
            request.getRequestDispatcher("/WEB-INF/view/account/resetPassword.jsp").forward(request, response);
            return;
        }

        AccountDAO dao = new AccountDAO();
        boolean updated = dao.updatePasswordByUsername(username, newPassword);

        if (updated) {
            session.removeAttribute("resetUsername");
            System.out.println("✅ Đặt lại mật khẩu thành công cho user: " + username);

            request.setAttribute("success", "Đặt lại mật khẩu thành công. Vui lòng đăng nhập.");
            request.getRequestDispatcher("/WEB-INF/view/account/login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Đặt lại mật khẩu thất bại. Vui lòng thử lại.");
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
        // Tránh vào trực tiếp không qua OTP
        response.sendRedirect("forgot-password");
    }

    @Override
    public String getServletInfo() {
        return "Handles password reset after OTP verification";
    }
}
