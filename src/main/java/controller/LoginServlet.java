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

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes both GET and POST requests for user login.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        if ("GET".equalsIgnoreCase(request.getMethod())) {
            // Display login form
            request.getRequestDispatcher("/WEB-INF/view/account/login.jsp").forward(request, response);
            return;
        }

        // Handle POST request: authenticate login
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AccountDAO dao = new AccountDAO();
        AccountDTO account = dao.login(username, password);

        if (account != null) {
            HttpSession session = request.getSession();
            session.setAttribute("account", account);

            String role = account.getRole();
            if ("admin".equals(role)) {
                response.sendRedirect("admin/dashboard.jsp");
            } else if ("staff".equals(role)) {
                response.sendRedirect("staff/dashboard.jsp");
            } else {
                response.sendRedirect("home.jsp");
            }
        } else {
            request.setAttribute("error", "Invalid username or password!");
            request.getRequestDispatcher("/WEB-INF/view/account/login.jsp").forward(request, response);
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
        return "Handles user login and role-based redirection.";
    }
    // </editor-fold>
}
