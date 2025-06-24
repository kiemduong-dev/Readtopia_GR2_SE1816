<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Export Stock List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-style.css">
</head>
<body>
<div class="main-content">
    <h2>📦 Export Stock List</h2>
    <a href="${pageContext.request.contextPath}/admin/stock/export/exportStockAdd" class="btn">➕ Add Export</a>

    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Customer</th>
            <th>Export Date</th>
            <th>Staff</th>
            <th>Total</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="e" items="${exportList}">
            <tr>
                <td>${e.exportID}</td>
                <td>${e.customerName}</td>
                <td>${e.exportDate}</td>
                <td>${e.staffName}</td>
                <td>${e.total}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/stock/export/detail?id=${e.exportID}">Details</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
