package controller.admin;

import dao.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 * AccountDeleteServlet
 *
 * This servlet is responsible for deleting a user account from the system.
 * It can be triggered via GET or POST requests from the admin dashboard.
 */
@WebServlet(name = "AccountDeleteServlet", urlPatterns = {"/admin/account/delete"})
public class AccountDeleteServlet extends HttpServlet {

    /**
     * Handles GET request for deleting a user account.
     * Typically used when triggered by hyperlink or button click.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Delegate to shared logic for deletion
        processDelete(request, response);
    }

    /**
     * Handles POST request for deleting a user account.
     * Recommended for secure operations via form submission.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Delegate to shared logic for deletion
        processDelete(request, response);
    }

    /**
     * Core method that performs the deletion logic.
     * This method:
     *  - Retrieves the username from request parameters.
     *  - Validates input.
     *  - Calls DAO to delete the user account.
     *  - Sets result message in session.
     *  - Redirects to the account listing page.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws IOException if redirection fails
     */
    private void processDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // Get username from query string or form
        String username = request.getParameter("username");

        // Get current session to store feedback message
        HttpSession session = request.getSession();

        // Step 1: Validate input
        if (username != null && !username.trim().isEmpty()) {

            // Step 2: Call DAO to delete account
            AccountDAO dao = new AccountDAO();
            boolean success = dao.deleteAccount(username.trim());

            // Step 3: Set session message based on result
            if (success) {
                session.setAttribute("message", "The account has been deleted successfully.");
            } else {
                session.setAttribute("message", "Account deletion failed. Please try again.");
            }

        } else {
            // Handle invalid or missing username input
            session.setAttribute("message", "Invalid username. Unable to proceed with deletion.");
        }

        // Step 4: Redirect user back to account listing page
        response.sendRedirect(request.getContextPath() + "/admin/account/list");
    }

    /**
     * Returns a short description of the servlet's purpose.
     *
     * @return String description
     */
    @Override
    public String getServletInfo() {
        return "Admin servlet for deleting a user account by username.";
    }
}
