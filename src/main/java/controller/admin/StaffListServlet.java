package controller.admin;

import dao.StaffDAO;
import dto.StaffDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

/**
 * StaffListServlet
 *
 * This servlet handles the retrieval and display of the list of staff members
 * for the admin dashboard. It supports both GET and POST requests.
 */
@WebServlet(name = "StaffListServlet", urlPatterns = {"/admin/staff/list"})
public class StaffListServlet extends HttpServlet {

    /**
     * Handles GET requests to display the staff list.
     *
     * Steps: 1. Retrieve the optional keyword parameter from the request. 2.
     * Use DAO to search or retrieve all staff depending on keyword presence. 3.
     * Set the list into request scope. 4. Forward the request to the
     * corresponding JSP page.
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if servlet fails
     * @throws IOException if I/O operation fails
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set character encoding to support UTF-8 inputs
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // Step 1: Get keyword parameter (for searching)
        String keyword = request.getParameter("keyword");

        // Step 2: Create DAO instance to access database
        StaffDAO dao = new StaffDAO();
        List<StaffDTO> list;

        // Step 3: Retrieve data based on whether a keyword is present
        if (keyword != null && !keyword.trim().isEmpty()) {
            // Search staff using keyword
            list = dao.searchStaffs(keyword.trim());

            // Save keyword back to request scope to refill search box in JSP
            request.setAttribute("keyword", keyword.trim());
        } else {
            // No search keyword → retrieve all staff accounts
            list = dao.getAllStaffs();
        }

        // Step 4: Pass result to view (JSP)
        request.setAttribute("staffs", list);
        request.getRequestDispatcher("/WEB-INF/view/admin/staff/list.jsp").forward(request, response);
    }

    /**
     * Handles POST requests (e.g., from a search form) by reusing doGet.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException in case of error
     * @throws IOException in case of I/O issue
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // POST is redirected to GET logic for shared handling
        doGet(request, response);
    }

    /**
     * Returns a short description of this servlet.
     *
     * @return description of servlet
     */
    @Override
    public String getServletInfo() {
        return "Displays list of staff members with optional keyword search functionality for admin users.";
    }
}
