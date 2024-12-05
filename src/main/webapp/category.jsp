<%--
  Created by IntelliJ IDEA.
  User: haith
  Date: 11/13/2024
  Time: 7:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
</head>
<body>
    <div class="container">
        <a href="${pageContext.request.contextPath}/category">Category</a>
        <a href="${pageContext.request.contextPath}/product">Product</a>
        <a href="${pageContext.request.contextPath}/statistics">Statistics</a>
        <h1>Category Form</h1>
        <mark>${message}</mark>
        <form action="${pageContext.request.contextPath}/category" method="post" class="form-control">
            <!-- Trường Id -->
            <label>Id:</label>
            <input class="form-control" name="categoryId"
                   value="${editCategory != null ? editCategory.id : ''}">

            <!-- Trường Name -->
            <label>Name: </label>
            <input class="form-control" name="categoryName"
                   value="${editCategory != null ? editCategory.name : ''}">

            <hr>

            <button class="btn btn-primary" name="action" value="create">Create</button>
            <button class="btn btn-dark" name="action" value="update">Update</button>
            <button class="btn btn-danger" name="action" value="delete">Delete</button>
        </form>


        <hr>

        <table class="table table-striped">
            <thead>
                <tr>
                    <td>No</td>
                    <td>Id</td>
                    <td>Name</td>
                    <td>Action</td>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="category" items="${categoryList}" varStatus="status">
                <tr>
                    <!-- Sử dụng status.count để hiển thị số thứ tự -->
                    <td>${status.count}</td>
                    <td>${category.id}</td>
                    <td>${category.name}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/category?categoryId=${category.id}">Edit</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
