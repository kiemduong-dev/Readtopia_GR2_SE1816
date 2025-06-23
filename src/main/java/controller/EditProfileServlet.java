package controller;

import dao.AccountDAO;
import dto.AccountDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;

/**
 * EditProfileServlet
 * 
 * This servlet handles both GET and POST requests:
 * - GET: Load profile edit form with current user data
 * - POST: Process the submitted profile update
 */
@WebServlet(name = "EditProfileServlet", urlPatterns = {"/edit-profile"})
public class EditProfileServlet extends HttpServlet {

    /**
     * Handles both GET and POST logic in a unified method.
     *
     * @param request  HttpServletRequest from the client
     * @param response HttpServletResponse to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set content type for response with UTF-8 encoding
        response.setContentType("text/html;charset=UTF-8");

        // Get existing session without creating a new one
        HttpSession session = request.getSession(false);

        // Retrieve the logged-in user from session
        AccountDTO acc = (session != null) ? (AccountDTO) session.getAttribute("account") : null;

        // If no user is logged in, redirect to login page
        if (acc == null) {
            response.sendRedirect("login");
            return;
        }

        // Handle GET: Show edit form with current profile data
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            request.setAttribute("user", acc); // Pass user data to view
            request.getRequestDispatcher("/WEB-INF/view/account/editProfile.jsp").forward(request, response);
            return;
        }

        // Handle POST: Process the submitted profile update
        try {
            // === Step 1: Extract form parameters ===
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String dobStr = request.getParameter("dob");
            String gender = request.getParameter("sex");

            // === Step 2: Convert gender string to integer (1 = Male, 0 = Female) ===
            int sex = "Male".equalsIgnoreCase(gender) ? 1 : 0;

            // === Step 3: Parse date of birth from String to SQL Date ===
            Date dob = Date.valueOf(dobStr);

            // === Step 4: Update account object with new values ===
            acc.setFirstName(firstName);
            acc.setLastName(lastName);
            acc.setEmail(email);
            acc.setPhone(phone);
            acc.setAddress(address);
            acc.setSex(sex);
            acc.setDob(dob);

            // === Step 5: Update database using DAO ===
            AccountDAO dao = new AccountDAO();
            boolean success = dao.updateProfile(acc);

            // === Step 6: Set response attributes based on result ===
            if (success) {
                session.setAttribute("account", acc); // Update session with new data
                request.setAttribute("success", "Profile updated successfully.");
            } else {
                request.setAttribute("error", "Failed to update profile. Please try again.");
            }

        } catch (Exception e) {
            // Catch and handle invalid input (date format, nulls, etc.)
            request.setAttribute("error", "Invalid input. Please verify all fields.");
        }

        // === Step 7: Always return to profile view ===
        request.setAttribute("user", acc);
        request.getRequestDispatcher("/WEB-INF/view/account/editProfile.jsp").forward(request, response);
    }

    /**
     * Handles GET request to show profile edit form.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles POST request to submit updated profile data.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Provides a short description of this servlet.
     *
     * @return String description
     */
    @Override
    public String getServletInfo() {
        return "Handles editing and updating user profile information.";
    }
}
