package controller.admin;

import dao.StaffDAO;
import dto.StaffDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 * StaffDeleteServlet
 *
 * This servlet handles the deletion of staff accounts by an admin. Only
 * non-admin staff (role != 0) can be deleted.
 */
@WebServlet(name = "StaffDeleteServlet", urlPatterns = {"/admin/staff/delete"})
public class StaffDeleteServlet extends HttpServlet {

    // DAO object to perform database operations
    private final StaffDAO dao = new StaffDAO();

    /**
     * Handles both GET and POST requests for staff deletion.
     *
     * Steps: 1. Get staff ID from request. 2. Validate the ID format. 3.
     * Retrieve the staff record by ID. 4. Only allow deletion if the staff is
     * not an admin (role != 0). 5. Set session message depending on result.
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if servlet-specific error occurs
     * @throws IOException if an input/output error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Ensure proper encoding for internationalization
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // Get current session to store user messages
        HttpSession session = request.getSession();

        // Step 1: Get the "id" parameter from the request
        String idParam = request.getParameter("id");

        // Step 2: Check if the ID parameter is provided and not empty
        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                // Step 3: Parse the ID into an integer
                int staffID = Integer.parseInt(idParam.trim());

                // Step 4: Fetch staff info from the database
                StaffDTO staff = dao.getStaffByID(staffID);

                // Step 5: Check if staff exists and is not an admin
                if (staff != null && staff.getRole() != 0) {
                    boolean deleted = dao.deleteStaffByID(staffID);

                    // Step 6: Store result message in session
                    if (deleted) {
                        session.setAttribute("message", "Staff deleted successfully.");
                    } else {
                        session.setAttribute("message", "Failed to delete staff.");
                    }
                } else {
                    // Staff not found or is an admin
                    session.setAttribute("message", "Invalid staff or admin account cannot be deleted.");
                }

            } catch (NumberFormatException e) {
                // ID format is invalid (not a number)
                session.setAttribute("message", "Invalid staff ID format.");
            }

        } else {
            // ID parameter is missing or empty
            session.setAttribute("message", "Staff ID is required.");
        }

        // Step 7: Redirect back to the staff list page
        response.sendRedirect("list");
    }

    /**
     * Handles GET requests by delegating to processRequest.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles POST requests by delegating to processRequest.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Provides a short description of this servlet.
     */
    @Override
    public String getServletInfo() {
        return "Handles deletion of staff accounts (excluding admin) by administrator.";
    }
}
