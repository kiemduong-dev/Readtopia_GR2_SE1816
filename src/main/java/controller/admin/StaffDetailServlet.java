package controller.admin;

import dao.StaffDAO;
import dto.StaffDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 * StaffDetailServlet
 *
 * This servlet handles GET requests to view the details of a staff member.
 * It retrieves staff data based on the provided staff ID (via "id" parameter)
 * and displays the result in a JSP view.
 */
@WebServlet(name = "StaffDetailServlet", urlPatterns = {"/admin/staff/detail"})
public class StaffDetailServlet extends HttpServlet {

    // DAO instance to perform database operations
    private final StaffDAO staffDAO = new StaffDAO();

    /**
     * Handles GET requests to retrieve and display staff details.
     *
     * Algorithm:
     *  1. Read the "id" parameter from the request.
     *  2. Validate the ID (non-empty and numeric).
     *  3. Query the database for the staff record.
     *  4. If found, forward to detail JSP; if not, redirect with message.
     *
     * @param request  HttpServletRequest object from client
     * @param response HttpServletResponse object to client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set request encoding to UTF-8 to handle Unicode data
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // Step 1: Get the staff ID parameter from request
        String idParam = request.getParameter("id");

        // Step 2: Validate the input (must not be null or blank)
        if (idParam == null || idParam.trim().isEmpty()) {
            request.getSession().setAttribute("message", "Staff ID is missing.");
            response.sendRedirect("list");
            return;
        }

        try {
            // Step 3: Parse the staff ID from the request parameter
            int staffID = Integer.parseInt(idParam.trim());

            // Step 4: Retrieve the staff information from database
            StaffDTO staff = staffDAO.getStaffByID(staffID);

            // Step 5: If staff exists, forward to detail JSP with data
            if (staff != null) {
                request.setAttribute("staff", staff);
                request.getRequestDispatcher("/WEB-INF/view/admin/staff/detail.jsp").forward(request, response);
            } else {
                // Staff not found → show message and redirect
                request.getSession().setAttribute("message", "Staff not found.");
                response.sendRedirect("list");
            }

        } catch (NumberFormatException e) {
            // Staff ID is not a valid number → show error
            request.getSession().setAttribute("message", "Invalid staff ID format.");
            response.sendRedirect("list");

        } catch (Exception e) {
            // General exception (e.g., DB error) → log and redirect
            request.getSession().setAttribute("message", "Error retrieving staff detail.");
            response.sendRedirect("list");
        }
    }

    /**
     * Handles POST requests by forwarding them to doGet().
     * This ensures the servlet only uses a single logic path for both methods.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException if a servlet error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Reuse logic in doGet to handle POST requests uniformly
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return description string
     */
    @Override
    public String getServletInfo() {
        return "Displays detailed information of a staff member based on ID.";
    }
}
