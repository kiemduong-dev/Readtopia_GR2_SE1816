/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.AccountDAO;
import dto.AccountDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "AccountDeleteServlet", urlPatterns = {"/admin/account/delete"})
public class AccountDeleteServlet extends HttpServlet {

    /**
     * Processes both GET and POST requests to delete a user account by username.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        if (username != null && !username.trim().isEmpty()) {
            AccountDAO dao = new AccountDAO();
            dao.deleteAccount(username); // Always redirect, no need to check result here
        }

        response.sendRedirect("list");
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
        return "Handles admin request to delete an account by username.";
    }
    // </editor-fold>
}
