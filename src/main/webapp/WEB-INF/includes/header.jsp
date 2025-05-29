<%@ include file="head.jsp" %>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark px-3">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">? ReadTopia</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="mainNavbar">
        <ul class="navbar-nav ms-auto">
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/home.jsp">Trang ch?</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/view/book/list.jsp">Sách</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/view/account/profile.jsp">Tài Kho?n</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout">??ng U?t</a></li>
        </ul>
    </div>
</nav>
