<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/includes/head-admin.jsp" />
<jsp:include page="/WEB-INF/includes/sidebar-admin.jsp" />

<div class="main-content">
    <div class="content-area">
        <h1>Edit Staff</h1>
        <form method="post" action="${pageContext.request.contextPath}/admin/staff/edit">
            <input type="hidden" name="staffID" value="${staff.staffID}" />
            <div class="form-group">
                <label>Username:</label>
                <input type="text" name="username" value="${staff.username}" required class="form-input" />
            </div>
            <div class="btn-group">
                <button type="submit" class="btn btn-primary">Update Staff</button>
                <a href="${pageContext.request.contextPath}/admin/staff/list" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
</div>
