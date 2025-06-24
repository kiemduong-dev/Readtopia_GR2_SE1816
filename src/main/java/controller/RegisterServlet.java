package controller;

import dao.AccountDAO;
import dto.AccountDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;
import java.util.Random;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

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
            if (!password.equals(confirmPassword)) {
                request.setAttribute("error", "Passwords do not match.");
                request.getRequestDispatcher("/WEB-INF/view/account/register.jsp").forward(request, response);
                return;
            }

            AccountDAO dao = new AccountDAO();

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
                    null // OTP: will set below
            );

            // === Generate and store OTP ===
            String otp = String.valueOf(100000 + new Random().nextInt(900000));
            session.setAttribute("otp", otp);
            session.setAttribute("pendingAccount", account);

            // (Optional) Gửi email nếu có MailUtil
            // MailUtil.send(email, "Your OTP code", "Your OTP is: " + otp);
            response.sendRedirect(request.getContextPath() + "/verifyOTP");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Registration failed due to invalid input or system error.");
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
