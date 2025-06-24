<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="/WEB-INF/includes/head-admin.jsp" />
<jsp:include page="/WEB-INF/includes/sidebar-admin.jsp" />

<div class="main-content">
    <div class="content-area">
        <h1>Staff Details</h1>
        <dl>
            <dt>Staff ID:</dt>
            <dd>${staff.staffID}</dd>
            <dt>Username:</dt>
            <dd>${staff.username}</dd>
        </dl>
        <a href="${pageContext.request.contextPath}/admin/staff/list" class="btn btn-secondary">Back to list</a>
    </div>
</div>
