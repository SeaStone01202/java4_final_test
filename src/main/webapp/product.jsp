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
  <h1>Product Form</h1>
  <mark>${message}</mark>
  <form action="${pageContext.request.contextPath}/product" method="post" class="form-control">
    <label>Category ?</label>
    <select name="categorySelected">
      <option value="all">All</option>
      <c:forEach var="categoryName" items="${categoryList}">
        <option value="${categoryName.id}">${categoryName.name}</option>
      </c:forEach>
    </select>
    <button class="btn btn-success" name="action" value="show">Show</button>
  </form>

  <form action="${pageContext.request.contextPath}/product" method="post" class="form-control">
    <label>Category ?</label>
    <input type="text" name="categoryName">
    <button class="btn btn-success" name="action" value="show2">Show</button>
  </form>

  <form action="${pageContext.request.contextPath}/product" method="post" class="form-control">
    <label>Category ?</label>
    <br>
    <input type="radio" name="categorySelected" value="all"> All
    <c:forEach var="categoryName" items="${categoryList}">
      <input type="radio" name="categorySelected" value="${categoryName.id}"> ${categoryName.name}
    </c:forEach>
    <button class="btn btn-warning" name="action" value="show">Show</button>
  </form>

  <hr>

  <table class="table table-striped">
    <thead>
    <tr>
      <td>Id</td>
      <td>Name</td>
      <td>Price</td>
      <td>Category Name</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${productList}">
      <tr>
        <!-- Sử dụng status.count để hiển thị số thứ tự -->
        <td>${product.id}</td>
        <td>${product.name}</td>
        <td>${product.price}</td>
        <td>${product.category.name}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>
