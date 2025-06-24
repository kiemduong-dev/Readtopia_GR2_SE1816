<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <jsp:include page="/WEB-INF/includes/head.jsp" />

    <body>
        <jsp:include page="/WEB-INF/includes/header.jsp" />

        <!-- Reset Password Section -->
        <div class="modal" style="display: block; background: none;">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h2>🔐 Reset Password</h2>
                    <button class="close" onclick="location.href = '${pageContext.request.contextPath}/login'">&times;</button>
                </div>

                <!-- Modal Body -->
                <div class="modal-body">

                    <!-- Logo branding -->
                    <div class="logo-section">
                        <div class="logo-bear"></div>
                        <div class="logo-text">READTOPIA</div>
                    </div>

                    <!-- Thông báo lỗi -->
                    <c:if test="${not empty error}">
                        <div class="success-message" style="background: #fdecea; color: #c62828;">
                            <i class="fas fa-exclamation-circle"></i>
                            <span>${error}</span>
                        </div>
                    </c:if>

                    <!-- Form -->
                    <form action="${pageContext.request.contextPath}/reset-password" method="post">
                        <div class="form-group">
                            <label class="form-label">New Password</label>
                            <input type="password" name="newPassword" class="form-input" required minlength="6" />
                        </div>

                        <div class="form-group">
                            <label class="form-label">Confirm Password</label>
                            <input type="password" name="confirmPassword" class="form-input" required minlength="6" />
                        </div>

                        <div class="btn-group mt-20">
                            <button type="submit" class="btn btn-primary">Save</button>
                            <a href="${pageContext.request.contextPath}/login" class="btn btn-secondary">Back to Login</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <jsp:include page="/WEB-INF/includes/footer.jsp" />
    </body>
</html>
