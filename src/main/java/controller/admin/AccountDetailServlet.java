package controller.admin;

import dao.AccountDAO;
import dto.AccountDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 * AccountDetailServlet
 *
 * This servlet handles displaying detailed information of a specific account
 * for administrative purposes. It supports GET requests only.
 */
@WebServlet(name = "AccountDetailServlet", urlPatterns = {"/admin/account/detail"})
public class AccountDetailServlet extends HttpServlet {

    /**
     * Handles GET requests to fetch and display account details.
     *
     * This method:
     *  1. Retrieves the "username" parameter from the request.
     *  2. Validates the username.
     *  3. Retrieves account data from the database via DAO.
     *  4. If found, forwards to the JSP view.
     *  5. Otherwise, redirects to the account list.
     *
     * @param request  HttpServletRequest from client
     * @param response HttpServletResponse to client
     * @throws ServletException in case of servlet error
     * @throws IOException      in case of I/O error
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Step 1: Retrieve the username parameter from the query string
        String username = request.getParameter("username");

        // Step 2: Validate the username input (not null or empty)
        if (username == null || username.trim().isEmpty()) {
            // Redirect to account list page if username is missing
            response.sendRedirect("list");
            return;
        }

        // Step 3: Trim whitespace and query the account from database
        AccountDAO dao = new AccountDAO();
        AccountDTO account = dao.getAccountByUsername(username.trim());

        // Step 4: Check if account exists
        if (account == null) {
            // If account does not exist, redirect back to the list page
            response.sendRedirect("list");
            return;
        }

        // Step 5: Set account as request attribute to pass to JSP view
        request.setAttribute("account", account);

        // Step 6: Forward request to detail.jsp for rendering account details
        request.getRequestDispatcher("/WEB-INF/view/admin/account/detail.jsp").forward(request, response);
    }

    /**
     * Handles POST requests by redirecting to the account list.
     * This servlet is read-only, so POST is not supported.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException not expected
     * @throws IOException      on redirection error
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect to list page since POST is not supported
        response.sendRedirect("list");
    }

    /**
     * Returns a short description of this servlet.
     *
     * @return String description
     */
    @Override
    public String getServletInfo() {
        return "Displays the detailed information of a user account for administrative view.";
    }
}
