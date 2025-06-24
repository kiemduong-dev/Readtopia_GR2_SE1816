<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/includes/head-admin.jsp" />
<jsp:include page="/WEB-INF/includes/sidebar-admin.jsp" />

<div class="main-content">
    <div class="content-area">
        <h1>Account Management</h1>
        <button class="btn btn-primary" onclick="location.href = '${pageContext.request.contextPath}/admin/account/add'">
            <i class="fas fa-plus"></i> Add Account
        </button>
        <table class="table" style="margin-top:15px;">
            <thead>
                <tr>
                    <th>Username</th>
                    <th>Full Name</th>
                    <th>Sex</th>
                    <th>Role</th>
                    <th>Email</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="acc" items="${accounts}">
                    <tr>
                        <td><c:out value="${acc.username}" /></td>
                        <td><c:out value="${acc.firstName} ${acc.lastName}" /></td>
                        <td><c:out value="${acc.sex == 1 ? 'Male' : 'Female'}" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${acc.role == 0}">Admin</c:when>
                                <c:when test="${acc.role == 1}">Customer</c:when>
                                <c:otherwise>Unknown</c:otherwise>
                            </c:choose>
                        </td>
                        <td><c:out value="${acc.email}" /></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/account/detail?username=${acc.username}" class="btn btn-info btn-icon" title="View"><i class="fas fa-eye"></i></a>
                            <a href="${pageContext.request.contextPath}/admin/account/edit?username=${acc.username}" class="btn btn-warning btn-icon" title="Edit"><i class="fas fa-edit"></i></a>
                            <!-- Thêm xóa nếu cần -->
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
