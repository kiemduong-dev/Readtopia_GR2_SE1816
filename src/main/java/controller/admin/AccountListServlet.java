package controller.admin;

import dao.AccountDAO;
import dto.AccountDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

/**
 * AccountListServlet
 *
 * This servlet is responsible for displaying the list of user accounts
 * to the admin. It supports keyword-based search functionality.
 */
@WebServlet(name = "AccountListServlet", urlPatterns = {"/admin/account/list"})
public class AccountListServlet extends HttpServlet {

    /**
     * Processes both GET and POST requests.
     *
     * Algorithm:
     * 1. Retrieve optional "keyword" parameter from request.
     * 2. If keyword is present and valid, perform a filtered search using DAO.
     * 3. If keyword is not provided, retrieve the full list of accounts.
     * 4. Set the result list in the request scope.
     * 5. Forward to JSP view for rendering.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException on servlet error
     * @throws IOException      on I/O error
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set response encoding to handle UTF-8 characters in search queries
        response.setContentType("text/html;charset=UTF-8");

        // Step 1: Get keyword from request parameter (can be null or empty)
        String keyword = request.getParameter("keyword");

        // Create DAO instance to interact with database
        AccountDAO dao = new AccountDAO();
        List<AccountDTO> accounts;

        // Step 2: If keyword is provided and not empty, search accounts
        if (keyword != null && !keyword.trim().isEmpty()) {
            // Perform search using trimmed keyword
            accounts = dao.searchAccounts(keyword.trim());

            // Store keyword back to request so UI can display it
            request.setAttribute("keyword", keyword.trim());
        } else {
            // Step 3: If no keyword, fetch full account list
            accounts = dao.getAllAccounts();
        }

        // Step 4: Set retrieved account list to request attribute
        request.setAttribute("accounts", accounts);

        // Step 5: Forward to JSP page to render the data
        request.getRequestDispatcher("/WEB-INF/view/admin/account/list.jsp").forward(request, response);
    }

    /**
     * Handles HTTP GET method.
     *
     * Typically triggered when admin navigates to the account list page.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException when dispatch fails
     * @throws IOException      on I/O error
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles HTTP POST method.
     *
     * Typically used when search form is submitted via POST.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException when dispatch fails
     * @throws IOException      on I/O error
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of this servlet.
     *
     * @return String describing the servlet
     */
    @Override
    public String getServletInfo() {
        return "Displays the list of all user accounts with optional search support for admin.";
    }
}
