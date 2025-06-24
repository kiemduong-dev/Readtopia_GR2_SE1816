<%@ page import="dto.ImportStockDetailDTO, java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/includes/head-admin.jsp" />
<jsp:include page="/WEB-INF/includes/sidebar-admin.jsp" />

<div class="main-content">
    <div class="content-area">
        <div class="page-header">
            <h2 class="page-title">Import Detail - ID: <%= request.getAttribute("isid") %></h2>
            <p class="page-subtitle">Details of the books in this import record</p>
        </div>

        <div class="card table-container">
            <table class="table">
                <thead>
                    <tr>
                        <th>Book ID</th>
                        <th>Book Title</th>
                        <th>Quantity</th>
                        <th>Price (VND)</th>
                        <th>Total (VND)</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<ImportStockDetailDTO> details = (List<ImportStockDetailDTO>) request.getAttribute("details");
                        for (ImportStockDetailDTO d : details) {
                    %>
                    <tr>
                        <td><%= d.getBookID() %></td>
                        <td><%= d.getBookTitle() %></td>
                        <td><%= d.getISDQuantity() %></td>
                        <td><%= String.format("%,.0f", d.getImportPrice()) %></td>
                        <td><%= String.format("%,.0f", d.getImportPrice() * d.getISDQuantity()) %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>

        <div class="btn-group">
            <a href="${pageContext.request.contextPath}/admin/stock/list?action=list" class="btn btn-secondary">Back to List</a>
        </div>
    </div>
</div>
