package controller.admin;

import dao.StaffDAO;
import dto.StaffDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;

/**
 * StaffAddServlet
 *
 * This servlet allows an admin to add a new staff member to the system.
 * It handles both GET (display form) and POST (process form) requests.
 */
@WebServlet(name = "StaffAddServlet", urlPatterns = {"/admin/staff/add"})
public class StaffAddServlet extends HttpServlet {

    /**
     * Handles GET requests by forwarding to the staff creation form JSP.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if server-side error occurs
     * @throws IOException      if input/output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to staff creation form
        request.getRequestDispatcher("/WEB-INF/view/admin/staff/add.jsp").forward(request, response);
    }

    /**
     * Handles POST requests to process staff creation.
     * Steps:
     *  1. Read form inputs from request.
     *  2. Validate and convert inputs.
     *  3. Create a StaffDTO object.
     *  4. Call DAO to save staff to database.
     *  5. Handle success/failure with appropriate feedback.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if server-side error occurs
     * @throws IOException      if input/output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set character encoding for internationalization
        request.setCharacterEncoding("UTF-8");

        // Get current session for storing feedback messages
        HttpSession session = request.getSession();

        // Step 1: Retrieve form data from request parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dobRaw = request.getParameter("dob");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String sexRaw = request.getParameter("sex");
        String roleRaw = request.getParameter("role");

        try {
            // Step 2: Convert string date to java.sql.Date
            Date dob = Date.valueOf(dobRaw);

            // Convert gender text to integer representation (1 = Male, 0 = Female)
            int sex = "Male".equalsIgnoreCase(sexRaw) ? 1 : 0;

            // Step 3: Map role string to numeric role ID
            int role;
            switch (roleRaw) {
                case "Admin":
                    role = 0;
                    break;
                case "Seller Staff":
                    role = 2;
                    break;
                case "Warehouse Staff":
                    role = 3;
                    break;
                default:
                    role = 1; // Default to Customer role
            }

            // Step 4: Create StaffDTO object with collected data
            StaffDTO staff = new StaffDTO(username, password, firstName, lastName,
                    dob, email, phone, role, address, sex, 1, null); // accStatus = 1 (active), code = null

            // Step 5: Use DAO to add staff to database
            boolean success = new StaffDAO().addStaff(staff);

            if (success) {
                // Store success message in session scope
                session.setAttribute("message", "Staff added successfully.");
                response.sendRedirect("list");
                return;
            } else {
                // Handle failure (e.g., duplicate username/email)
                request.setAttribute("error", "Failed to add staff. Username or email may already exist.");
                request.setAttribute("staff", staff); // Preserve form input for redisplay
            }

        } catch (Exception e) {
            // Handle exception (e.g., invalid date format, null values)
            request.setAttribute("error", "Invalid input: " + e.getMessage());
        }

        // Forward back to form view with error message
        request.getRequestDispatcher("/WEB-INF/view/admin/staff/add.jsp").forward(request, response);
    }

    /**
     * Returns a brief description of this servlet.
     *
     * @return description string
     */
    @Override
    public String getServletInfo() {
        return "Handles the creation of staff accounts for administrative use.";
    }
}
