<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Chỉnh sửa sách - Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">✏️ Chỉnh sửa sách</h2>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/admin/book/edit">
        <input type="hidden" name="id" value="${book.bookID}">

        <div class="row">
            <div class="col-md-6">
                <label>Tiêu đề:</label>
                <input type="text" name="title" class="form-control" value="${book.bookTitle}" required>

                <label class="mt-2">Tác giả:</label>
                <input type="text" name="author" class="form-control" value="${book.author}">

                <label class="mt-2">Người dịch:</label>
                <input type="text" name="translator" class="form-control" value="${book.translator}">

                <label class="mt-2">Nhà xuất bản:</label>
                <input type="text" name="publisher" class="form-control" value="${book.publisher}">

                <label class="mt-2">Năm xuất bản:</label>
                <input type="number" name="year" class="form-control" value="${book.publicationYear}">

                <label class="mt-2">ISBN:</label>
                <input type="text" name="isbn" class="form-control" value="${book.isbn}">

                <label class="mt-2">Link ảnh:</label>
                <input type="text" name="image" class="form-control" value="${book.image}">
            </div>

            <div class="col-md-6">
                <label>Mô tả:</label>
                <textarea name="description" class="form-control" rows="5">${book.bookDescription}</textarea>

                <label class="mt-2">Bìa cứng (1: Có, 0: Không):</label>
                <input type="number" name="hardcover" class="form-control" value="${book.hardcover}">

                <label class="mt-2">Kích thước:</label>
                <input type="text" name="dimension" class="form-control" value="${book.dimension}">

                <label class="mt-2">Trọng lượng (g):</label>
                <input type="number" step="0.01" name="weight" class="form-control" value="${book.weight}">

                <label class="mt-2">Giá:</label>
                <input type="number" step="0.01" name="price" class="form-control" value="${book.bookPrice}">

                <label class="mt-2">Số lượng:</label>
                <input type="number" name="quantity" class="form-control" value="${book.bookQuantity}">

                <label class="mt-2">Trạng thái (1: Hiển thị, 0: Ẩn):</label>
                <input type="number" name="status" class="form-control" value="${book.bookStatus}">
            </div>
        </div>

        <button class="btn btn-success mt-4" type="submit">💾 Cập nhật sách</button>
        <a href="${pageContext.request.contextPath}
