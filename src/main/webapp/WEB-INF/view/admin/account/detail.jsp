<%-- 
    Document   : detail
    Created on : May 27, 2025, 8:25:25 PM
    Author     : ADMIN
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dto.AccountDTO" %>
<%@ include file="../../includes/header.jsp" %>

<jsp:useBean id="account" type="dto.AccountDTO" scope="request" />

<div class="container mt-5 mb-5">
    <div class="card shadow-sm mx-auto" style="max-width: 600px;">
        <div class="card-body">
            <h3 class="card-title text-center mb-4">👤 Thông tin chi tiết tài khoản</h3>

            <table class="table table-bordered">
                <tr>
                    <th>Username</th>
                    <td>${account.username}</td>
                </tr>
                <tr>
                    <th>Họ và tên</th>
                    <td>${account.fullName}</td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td>${account.email}</td>
                </tr>
                <tr>
                    <th>Điện thoại</th>
                    <td>${account.phone}</td>
                </tr>
                <tr>
                    <th>Địa chỉ</th>
                    <td>${account.address}</td>
                </tr>
                <tr>
                    <th>Vai trò</th>
                    <td><span class="badge bg-secondary">${account.role}</span></td>
                </tr>
                <tr>
                    <th>Trạng thái</th>
                    <td>
                        <c:choose>
                            <c:when test="${account.accStatus == 1}">
                                <span class="badge bg-success">Hoạt động</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge bg-danger">Khoá</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </table>

            <div class="text-center mt-3">
                <a href="${pageContext.request.contextPath}/admin/account/edit?username=${account.username}" class="btn btn-outline-warning btn-sm">✏️ Chỉnh sửa</a>
                <a href="${pageContext.request.contextPath}/admin/account/list" class="btn btn-outline-secondary btn-sm">← Quay lại danh sách</a>
            </div>
        </div>
    </div>
</div>
<%@ include file="../../includes/footer.jsp" %>
