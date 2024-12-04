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
    <h1>Number of Products in Category</h1>
    <mark>${message}</mark>


    <table class="table table-striped">
        <thead>
        <tr>
            <td>Category Name</td>
            <td>Number of Products</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="entry" items="${categoryCounts}">
            <tr>
                <td>${entry.key}</td>  <!-- Tên danh mục -->
                <td>${entry.value}</td> <!-- Số lượng sản phẩm -->
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
