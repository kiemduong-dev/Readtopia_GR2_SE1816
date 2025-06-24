<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add Export Stock</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-style.css">
</head>
<body>
<div class="main-content">
    <h2>➕ Add Export Stock</h2>

    <form action="${pageContext.request.contextPath}/admin/stock/export/exportStockAdd" method="post">
        <label>Customer Name:</label>
        <input type="text" name="customerName" required>

        <label>Export Date:</label>
        <input type="date" name="exportDate" required>

        <label>Staff:</label>
        <select name="staffName" required>
            <c:forEach var="s" items="${staffList}">
                <option value="${s.username}">${s.username}</option>
            </c:forEach>
        </select>

        <table class="table">
            <thead>
            <tr>
                <th>Select</th>
                <th>Book Title</th>
                <th>Quantity</th>
                <th>Unit Price</th>
                <th>Reason</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="b" items="${bookList}" varStatus="loop">
                <tr>
                    <td>
                        <input type="checkbox" name="selectedIndex" value="${loop.index}" onchange="toggleRow(this)">
                        <input type="hidden" name="bookID[]" value="${b.bookID}">
                    </td>
                    <td>${b.bookTitle}</td>
                    <td><input type="number" name="quantity[]" min="1" value="1" disabled></td>
                    <td><input type="number" name="price[]" step="0.01" min="0" value="${b.bookPrice}" disabled></td>
                    <td><input type="text" name="reason[]" placeholder="Reason..." disabled></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <button type="submit">Save Export</button>
    </form>
</div>

<script>
    function toggleRow(checkbox) {
        const row = checkbox.closest("tr");
        const inputs = row.querySelectorAll("input[type='number'], input[type='text']");
        inputs.forEach(input => input.disabled = !checkbox.checked);
    }
</script>
</body>
</html>
