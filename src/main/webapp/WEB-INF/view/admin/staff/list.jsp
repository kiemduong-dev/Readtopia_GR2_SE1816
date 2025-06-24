<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/includes/head-admin.jsp" />
<jsp:include page="/WEB-INF/includes/sidebar-admin.jsp" />

<div class="main-content">
    <div class="content-area">
        <div class="page-header">
            <h1 class="page-title">Staff Management</h1>
        </div>

        <button class="btn btn-primary" onclick="location.href = '${pageContext.request.contextPath}/admin/staff/add'">
            <i class="fas fa-plus"></i> Add Staff
        </button>

        <div class="table-container" style="margin-top: 15px;">
            <table class="table">
                <thead>
                    <tr>
                        <th>Staff ID</th>
                        <th>Username</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="staff" items="${staffs}">
                    <tr>
                        <td><c:out value="${staff.staffID}" /></td>
                    <td><c:out value="${staff.username}" /></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/staff/detail?staffID=${staff.staffID}" class="btn btn-icon btn-info" title="View">
                            <i class="fas fa-eye"></i>
                        </a>
                        <a href="${pageContext.request.contextPath}/admin/staff/edit?staffID=${staff.staffID}" class="btn btn-icon btn-warning" title="Edit">
                            <i class="fas fa-edit"></i>
                        </a>
                        <!-- Nếu cần, thêm nút xóa tại đây -->
                    </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
