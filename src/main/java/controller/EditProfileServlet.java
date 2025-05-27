/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dto.AccountDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "EditProfileServlet", urlPatterns = {"/edit-profile"})
public class EditProfileServlet extends HttpServlet {

    /**
     * Processes both GET and POST requests for editing user profile.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        AccountDTO acc = (AccountDTO) session.getAttribute("account");

        if (acc == null) {
            response.sendRedirect("login");
            return;
        }

        if ("GET".equalsIgnoreCase(request.getMethod())) {
            // Show edit profile form
            request.setAttribute("user", acc);
            request.getRequestDispatcher("/WEB-INF/view/account/editProfile.jsp").forward(request, response);
            return;
        }

        // Handle POST: update profile
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        acc.setFullName(fullname);
        acc.setEmail(email);
        acc.setPhone(phone);
        acc.setAddress(address);

        AccountDAO dao = new AccountDAO();
        boolean success = dao.updateProfile(acc);

        if (success) {
            session.setAttribute("account", acc);
            response.sendRedirect("profile");
        } else {
            request.setAttribute("user", acc);
            request.setAttribute("error", "Update failed!");
            request.getRequestDispatcher("/WEB-INF/view/account/editProfile.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
        return "Handles user profile editing functionality.";
    }
    // </editor-fold>
}
