<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.ExportStockDetailDTO, dto.BookDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Export Stock Details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-style.css">
</head>
<body>
<div class="main-content">
    <h2>📝 Export Details #${exportID}</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Book</th>
            <th>Quantity</th>
            <th>Unit Price</th>
            <th>Reason</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="d" items="${detailList}">
            <tr>
                <td>${bookMap[d.bookID]}</td>
                <td>${d.quantity}</td>
                <td>${d.price}</td>
                <td>${d.reason}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/admin/stock/export/exportStock">⬅ Back to list</a>
</div>
</body>
</html>
