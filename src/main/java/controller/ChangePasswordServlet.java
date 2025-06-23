package controller;

import dao.AccountDAO;
import dto.AccountDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 * ChangePasswordServlet
 *
 * This servlet handles both GET and POST requests for updating the user's
 * password. GET: Display the password change form. POST: Validate and apply the
 * new password if the old one is correct.
 */
@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/change-password"})
public class ChangePasswordServlet extends HttpServlet {

    /**
     * Unified method for handling both GET and POST HTTP requests.
     *
     * @param request HttpServletRequest from client
     * @param response HttpServletResponse to client
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set response encoding to support UTF-8 characters
        response.setContentType("text/html;charset=UTF-8");

        // Get current session (do not create a new one if not exists)
        HttpSession session = request.getSession(false);

        // Get the currently logged-in account from session
        AccountDTO acc = (session != null) ? (AccountDTO) session.getAttribute("account") : null;

        // If user is not logged in, redirect to login page
        if (acc == null) {
            response.sendRedirect("login");
            return;
        }

        // Handle GET request: show the change password form
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            request.getRequestDispatcher("/WEB-INF/view/account/changePassword.jsp").forward(request, response);
            return;
        }

        // === Handle POST request (user submitted the form) ===
        // Step 1: Retrieve form inputs
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // Step 2: Validate that new password and confirmation match
        if (newPassword == null || !newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Confirm password does not match.");
            request.getRequestDispatcher("/WEB-INF/view/account/changePassword.jsp").forward(request, response);
            return;
        }

        // Step 3: Use DAO to check and update password if old password is correct
        AccountDAO dao = new AccountDAO();
        boolean success = dao.updatePasswordByOld(acc.getUsername(), oldPassword, newPassword);

        // Step 4: Handle success or failure
        if (success) {
            // Update session with new password
            acc.setPassword(newPassword);
            session.setAttribute("account", acc);

            // Notify user of success
            request.setAttribute("success", "Password updated successfully.");
        } else {
            // Notify user that old password is incorrect
            request.setAttribute("error", "Old password is incorrect.");
        }

        // Step 5: Return to change password form with message
        request.getRequestDispatcher("/WEB-INF/view/account/changePassword.jsp").forward(request, response);
    }

    /**
     * Handles GET request: display the change password form.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles POST request: process the password update.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of this servlet.
     */
    @Override
    public String getServletInfo() {
        return "Handles user password change functionality.";
    }
}
