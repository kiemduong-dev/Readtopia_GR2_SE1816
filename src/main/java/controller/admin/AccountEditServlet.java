package controller.admin;

import dao.AccountDAO;
import dto.AccountDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;

/**
 * AccountEditServlet
 *
 * This servlet allows administrators to edit user account information. It
 * supports both GET (display edit form) and POST (process update).
 */
@WebServlet(name = "AccountEditServlet", urlPatterns = {"/admin/account/edit"})
public class AccountEditServlet extends HttpServlet {

    // DAO instance used for performing account-related database operations
    private final AccountDAO dao = new AccountDAO();

    /**
     * Handles GET requests to load the edit form with existing account data.
     *
     * Steps: 1. Get "username" parameter from the request. 2. Validate that the
     * username is provided. 3. Retrieve the corresponding account from the
     * database. 4. If found, forward the account to the edit JSP view. 5. If
     * not found, redirect to account list.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Step 1: Extract username from request parameter
        String username = request.getParameter("username");

        // Step 2: If username is invalid or missing, redirect to list
        if (username == null || username.trim().isEmpty()) {
            response.sendRedirect("list");
            return;
        }

        // Step 3: Retrieve account from database
        AccountDTO acc = dao.getAccountByUsername(username.trim());

        // Step 4: If account is not found, redirect to list
        if (acc == null) {
            response.sendRedirect("list");
            return;
        }

        // Step 5: Set account object in request and forward to JSP for editing
        request.setAttribute("account", acc);
        request.getRequestDispatcher("/WEB-INF/view/admin/account/edit.jsp").forward(request, response);
    }

    /**
     * Handles POST requests to update account information in the database.
     *
     * Steps: 1. Parse and validate form input. 2. Construct a DTO from form
     * data. 3. Update the account in the database. 4. If update succeeds,
     * redirect to list. Otherwise, return error and show form.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Step 1: Set encoding to handle UTF-8 form data
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {
            // Step 2: Collect and trim all form input fields
            String username = request.getParameter("username").trim();
            String firstName = request.getParameter("firstName").trim();
            String lastName = request.getParameter("lastName").trim();
            String email = request.getParameter("email").trim();
            String phone = request.getParameter("phone").trim();
            String address = request.getParameter("address").trim();
            String dobRaw = request.getParameter("dob");
            int role = Integer.parseInt(request.getParameter("role"));
            int sex = Integer.parseInt(request.getParameter("sex"));

            // Step 3: Convert raw date input into java.sql.Date
            Date dob = (dobRaw != null && !dobRaw.trim().isEmpty())
                    ? Date.valueOf(dobRaw)
                    : null;

            // Step 4: Populate AccountDTO with collected form data
            AccountDTO acc = new AccountDTO();
            acc.setUsername(username);
            acc.setFirstName(firstName);
            acc.setLastName(lastName);
            acc.setEmail(email);
            acc.setPhone(phone);
            acc.setAddress(address);
            acc.setRole(role);
            acc.setSex(sex);
            acc.setDob(dob);

            // Step 5: Attempt to update the account in the database
            boolean updated = dao.updateAccountByAdmin(acc);

            if (updated) {
                // Step 6a: If update successful, redirect to list page
                response.sendRedirect("list");
            } else {
                // Step 6b: If update fails, return to edit form with error message
                request.setAttribute("error", "Failed to update account. Please verify the data.");
                request.setAttribute("account", acc); // Retain filled form values
                request.getRequestDispatcher("/WEB-INF/view/admin/account/edit.jsp").forward(request, response);
            }

        } catch (Exception e) {
            // Step 7: Handle exceptions (parsing errors, null values, SQL issues)
            e.printStackTrace(); // Optional: use logger for production
            request.setAttribute("error", "Invalid input. Please check the entered values.");
            request.getRequestDispatcher("/WEB-INF/view/admin/account/edit.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet's purpose.
     *
     * @return description string
     */
    @Override
    public String getServletInfo() {
        return "Allows administrators to edit existing user account information.";
    }
}
