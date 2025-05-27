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
import java.util.List;

@WebServlet(name = "StaffListServlet", urlPatterns = {"/admin/staff/list"})
public class StaffListServlet extends HttpServlet {

    /**
     * Processes GET and POST requests to display the list of staff accounts.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String keyword = request.getParameter("keyword");
        AccountDAO dao = new AccountDAO();
        List<AccountDTO> staffs;

        if (keyword != null && !keyword.trim().isEmpty()) {
            staffs = dao.searchStaffs(keyword.trim());
            request.setAttribute("keyword", keyword);
        } else {
            staffs = dao.getAllStaffs();
        }

        request.setAttribute("staffs", staffs);
        request.getRequestDispatcher("/WEB-INF/view/admin/staff/list.jsp").forward(request, response);
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
        return "Displays list of all staff accounts for admin (with optional search).";
    }
    // </editor-fold>
}
