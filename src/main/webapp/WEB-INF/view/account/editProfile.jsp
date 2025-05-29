<%-- 
    Document   : editProfile
    Created on : May 27, 2025, 8:18:18 PM
    Author     : ADMIN
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dto.AccountDTO" %>
<%@ include file="../../includes/header.jsp" %>

<jsp:useBean id="user" type="dto.AccountDTO" scope="request" />

<div class="container mt-5 mb-5">
    <div class="card mx-auto shadow-sm" style="max-width: 600px;">
        <div class="card-body">
            <h3 class="text-center mb-4">✏️ Chỉnh sửa hồ sơ</h3>

            <form action="${pageContext.request.contextPath}/edit-profile" method="post">
                <div class="mb-3">
                    <label class="form-label">Họ và tên</label>
                    <input type="text" class="form-control" name="fullname" value="${user.fullName}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" class="form-control" name="email" value="${user.email}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Số điện thoại</label>
                    <input type="text" class="form-control" name="phone" value="${user.phone}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Địa chỉ</label>
                    <input type="text" class="form-control" name="address" value="${user.address}" required>
                </div>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger py-1 small">${error}</div>
                </c:if>
                <c:if test="${not empty success}">
                    <div class="alert alert-success py-1 small">${success}</div>
                </c:if>

                <div class="d-grid">
                    <button type="submit" class="btn btn-primary">Cập nhật thông tin</button>
                </div>
            </form>

            <div class="text-center mt-3">
                <a href="${pageContext.request.contextPath}/profile" class="text-muted small">← Quay lại hồ sơ</a>
            </div>
        </div>
    </div>
</div>

<%@ include file="../../includes/footer.jsp" %>
