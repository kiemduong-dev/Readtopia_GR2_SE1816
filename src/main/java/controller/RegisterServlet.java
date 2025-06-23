package controller;

import dao.AccountDAO;
import dto.AccountDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String dobStr = request.getParameter("dob");
        String gender = request.getParameter("sex");

        int sex = "male".equalsIgnoreCase(gender) ? 1 : 0;

        try {
            // Kiểm tra xác nhận mật khẩu
            if (!password.equals(confirmPassword)) {
                request.setAttribute("error", "Passwords do not match.");
                request.getRequestDispatcher("/WEB-INF/view/account/register.jsp").forward(request, response);
                return;
            }

            AccountDAO dao = new AccountDAO();

            // Kiểm tra trùng username hoặc email
            if (dao.getAccountByUsername(username) != null || dao.findByEmail(email) != null) {
                request.setAttribute("error", "Username or email already exists.");
                request.getRequestDispatcher("/WEB-INF/view/account/register.jsp").forward(request, response);
                return;
            }

            Date dob = Date.valueOf(dobStr);

            AccountDTO account = new AccountDTO(
                    username, password, firstName, lastName,
                    dob, email, phone,
                    1, // Role: Customer
                    address,
                    sex,
                    1, // Status: Active
                    null // OTP: bỏ qua
            );

            // ✅ Gọi đúng hàm addAccount trong DAO
            boolean added = dao.addAccount(account);

            if (added) {
                request.setAttribute("success", "Account registered successfully! Please log in.");
                request.getRequestDispatcher("/WEB-INF/view/account/login.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Failed to register account. Please try again.");
                request.getRequestDispatcher("/WEB-INF/view/account/register.jsp").forward(request, response);
            }

        } catch (Exception e) {
            System.err.println("❌ Registration error: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "Registration failed: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/view/account/register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/account/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
