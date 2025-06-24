<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/includes/head-admin.jsp" />
<jsp:include page="/WEB-INF/includes/sidebar-admin.jsp" />

<div class="main-content">
    <div class="content-area">
        <div class="page-header">
            <h1 class="page-title">Account Details: <c:out value="${account.username}" /></h1>
        </div>

        <div class="card">
            <table class="table">
                <tbody>
                    <tr>
                        <th>Username</th>
                        <td><c:out value="${account.username}" /></td>
                    </tr>
                    <tr>
                        <th>First Name</th>
                        <td><c:out value="${account.firstName}" /></td>
                    </tr>
                    <tr>
                        <th>Last Name</th>
                        <td><c:out value="${account.lastName}" /></td>
                    </tr>
                    <tr>
                        <th>Sex</th>
                        <td>
                            <c:choose>
                                <c:when test="${account.sex == 1}">Male</c:when>
                                <c:when test="${account.sex == 0}">Female</c:when>
                                <c:otherwise>Unknown</c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <th>Role</th>
                        <td>
                            <c:choose>
                                <c:when test="${account.role == 0}">Admin</c:when>
                                <c:when test="${account.role == 1}">Customer</c:when>
                                <c:otherwise>Unknown</c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <th>Email</th>
                        <td><c:out value="${account.email}" /></td>
                    </tr>
                </tbody>
            </table>

            <a href="${pageContext.request.contextPath}/admin/account/list" class="btn btn-secondary mt-3">Back to list</a>
        </div>
    </div>
</div>
