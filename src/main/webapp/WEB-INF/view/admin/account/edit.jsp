<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/includes/head-admin.jsp" />
<jsp:include page="/WEB-INF/includes/sidebar-admin.jsp" />

<div class="main-content">
    <div class="content-area">
        <h1>Edit Account: <c:out value="${account.username}" /></h1>
        <form method="post" action="${pageContext.request.contextPath}/admin/account/edit">
            <input type="hidden" name="username" value="${account.username}" />
            <div class="form-group">
                <label>First Name:</label>
                <input type="text" name="firstName" value="${account.firstName}" required class="form-input" />
            </div>
            <div class="form-group">
                <label>Last Name:</label>
                <input type="text" name="lastName" value="${account.lastName}" required class="form-input" />
            </div>
            <div class="form-group">
                <label>Sex:</label>
                <select name="sex" class="form-select" required>
                    <option value="1" <c:if test="${account.sex == 1}">selected</c:if>>Male</option>
                    <option value="0" <c:if test="${account.sex == 0}">selected</c:if>>Female</option>
                </select>
            </div>
            <div class="form-group">
                <label>Role:</label>
                <select name="role" class="form-select" required>
                    <option value="0" <c:if test="${account.role == 0}">selected</c:if>>Admin</option>
                    <option value="1" <c:if test="${account.role == 1}">selected</c:if>>Customer</option>
                </select>
            </div>
            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" value="${account.email}" required class="form-input" />
            </div>
            <div class="btn-group">
                <button type="submit" class="btn btn-primary">Update Account</button>
                <a href="${pageContext.request.contextPath}/admin/account/list" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
</div>
