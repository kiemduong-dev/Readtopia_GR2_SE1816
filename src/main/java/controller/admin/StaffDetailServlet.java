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

@WebServlet(name = "StaffDetailServlet", urlPatterns = {"/admin/staff/detail"})
public class StaffDetailServlet extends HttpServlet {

    /**
     * Processes GET and POST requests to show staff detail.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        AccountDAO dao = new AccountDAO();
        AccountDTO staff = dao.getAccountByUsername(username);

        if (staff == null || !"staff".equalsIgnoreCase(staff.getRole())) {
            response.sendRedirect("list");
            return;
        }

        request.setAttribute("staff", staff);
        request.getRequestDispatcher("/WEB-INF/view/admin/staff/detail.jsp").forward(request, response);
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
        return "Displays detailed information of a staff account.";
    }
    // </editor-fold>
}
