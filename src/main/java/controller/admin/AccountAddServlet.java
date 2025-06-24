package controller.admin;

import dao.AccountDAO;
import dto.AccountDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;

/**
 * Servlet that handles the functionality for an admin to add a new account.
 * This servlet supports both GET (display form) and POST (handle submission).
 */
@WebServlet(name = "AccountAddServlet", urlPatterns = {"/admin/account/add"})
public class AccountAddServlet extends HttpServlet {

    /**
     * Handles GET request to display the account creation form.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to JSP form for adding new account
        request.getRequestDispatcher("/WEB-INF/view/admin/account/add.jsp").forward(request, response);
    }

    /**
     * Handles POST request to process new account creation.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set request and response encoding
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {
            // ===== Retrieve form input =====
            String username = request.getParameter("username");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            int role = Integer.parseInt(request.getParameter("role"));
            int sex = Integer.parseInt(request.getParameter("sex"));

            // Parse date of birth if provided
            String dobRaw = request.getParameter("dob");
            Date dob = (dobRaw != null && !dobRaw.trim().isEmpty()) ? Date.valueOf(dobRaw) : null;

            // Default password for new account
            String password = "123456";

            // ===== Create DTO from form =====
            AccountDTO acc = new AccountDTO(username, password, firstName, lastName, dob, email, phone,
                    role, address, sex, 1, null); // accStatus = 1 (active), code = null

            // ===== Insert into DB =====
            AccountDAO dao = new AccountDAO();
            boolean success = dao.addAccount(acc);

            if (success) {
                // Redirect to account list if added successfully
                response.sendRedirect("list");
            } else {
                // Handle duplicate username/email
                request.setAttribute("error", "Failed to add account. Username or email may already exist.");
                request.setAttribute("account", acc); // Preserve input on error
                request.getRequestDispatcher("/WEB-INF/view/admin/account/add.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            // General error message for bad input or exceptions
            request.setAttribute("error", "Invalid input data. Please check the fields.");
            request.getRequestDispatcher("/WEB-INF/view/admin/account/add.jsp").forward(request, response);
        }
    }

    /**
     * @return Short servlet description
     */
    @Override
    public String getServletInfo() {
        return "Handles admin functionality to add a new user account.";
    }
}
