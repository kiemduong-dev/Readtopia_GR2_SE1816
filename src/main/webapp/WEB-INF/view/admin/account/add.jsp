<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="/WEB-INF/includes/head-admin.jsp" />
<jsp:include page="/WEB-INF/includes/sidebar-admin.jsp" />

<div class="main-content">
    <div class="content-area">
        <h1>Add New Account</h1>
        <form method="post" action="${pageContext.request.contextPath}/admin/account/add">
            <div class="form-group">
                <label>Username:</label>
                <input type="text" name="username" required class="form-input" />
            </div>
            <div class="form-group">
                <label>Password:</label>
                <input type="password" name="password" required class="form-input" />
            </div>
            <div class="form-group">
                <label>First Name:</label>
                <input type="text" name="firstName" required class="form-input" />
            </div>
            <div class="form-group">
                <label>Last Name:</label>
                <input type="text" name="lastName" required class="form-input" />
            </div>
            <div class="form-group">
                <label>Sex:</label>
                <select name="sex" class="form-select" required>
                    <option value="1">Male</option>
                    <option value="0">Female</option>
                </select>
            </div>
            <div class="form-group">
                <label>Role:</label>
                <select name="role" class="form-select" required>
                    <option value="0">Admin</option>
                    <option value="1">Customer</option>
                </select>
            </div>
            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" required class="form-input" />
            </div>
            <div class="btn-group">
                <button type="submit" class="btn btn-primary">Add Account</button>
                <a href="${pageContext.request.contextPath}/admin/account/list" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
</div>
