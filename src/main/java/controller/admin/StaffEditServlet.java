package controller.admin;

import dao.StaffDAO;
import dto.StaffDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;

/**
 * StaffEditServlet
 *
 * This servlet handles the logic for editing staff information by the admin. It
 * supports both GET (to load the edit form) and POST (to submit changes).
 */
@WebServlet(name = "StaffEditServlet", urlPatterns = {"/admin/staff/edit"})
public class StaffEditServlet extends HttpServlet {

    // DAO instance to interact with the staff table in the database
    private final StaffDAO staffDAO = new StaffDAO();

    /**
     * Handles GET requests to load staff information into the edit form.
     *
     * Algorithm: 1. Read the "id" parameter from request. 2. Validate if ID is
     * present and numeric. 3. Retrieve staff information using the DAO. 4. If
     * found, forward to edit.jsp with staff data. 5. If not found or invalid,
     * redirect to staff list.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // Step 1: Retrieve staff ID from query string
        String idParam = request.getParameter("id");

        // Step 2: Check if ID is missing or empty
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect("list");
            return;
        }

        try {
            // Step 3: Convert ID to integer
            int staffID = Integer.parseInt(idParam);

            // Step 4: Retrieve staff info from database
            StaffDTO staff = staffDAO.getStaffByID(staffID);

            // Step 5: If not found, redirect; otherwise, forward to JSP
            if (staff == null) {
                response.sendRedirect("list");
                return;
            }

            request.setAttribute("staff", staff);
            request.getRequestDispatcher("/WEB-INF/view/admin/staff/edit.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // If ID is not a valid number, redirect
            response.sendRedirect("list");
        }
    }

    /**
     * Handles POST requests to update staff information.
     *
     * Algorithm: 1. Read and validate all form parameters. 2. Convert data
     * types as needed (e.g., Date, int). 3. Create and populate StaffDTO. 4.
     * Call DAO to update staff record in the database. 5. Redirect or forward
     * based on success/failure.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {
            // Step 1: Retrieve and parse form fields
            int staffID = Integer.parseInt(request.getParameter("staffID"));
            String username = request.getParameter("username");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            Date dob = Date.valueOf(request.getParameter("dob"));
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            int sex = Integer.parseInt(request.getParameter("sex"));
            int role = Integer.parseInt(request.getParameter("role"));
            String address = request.getParameter("address");

            // Step 2: Create and populate DTO object
            StaffDTO staff = new StaffDTO();
            staff.setStaffID(staffID);
            staff.setUsername(username);
            staff.setFirstName(firstName);
            staff.setLastName(lastName);
            staff.setDob(dob);
            staff.setEmail(email);
            staff.setPhone(phone);
            staff.setSex(sex);
            staff.setRole(role);
            staff.setAddress(address);
            staff.setAccStatus(1); // Active account
            staff.setCode(null);   // No verification code by default

            // Step 3: Attempt update via DAO
            boolean success = staffDAO.updateStaff(staff);

            // Step 4: Redirect or forward based on result
            if (success) {
                response.sendRedirect("list");
            } else {
                request.setAttribute("error", "Failed to update staff.");
                request.setAttribute("staff", staff);
                request.getRequestDispatcher("/WEB-INF/view/admin/staff/edit.jsp").forward(request, response);
            }

        } catch (Exception e) {
            // Handle all types of input or update errors
            request.setAttribute("error", "Invalid input. Please check all fields.");
            request.getRequestDispatcher("/WEB-INF/view/admin/staff/edit.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return String description
     */
    @Override
    public String getServletInfo() {
        return "Handles editing of staff accounts by admin.";
    }
}
