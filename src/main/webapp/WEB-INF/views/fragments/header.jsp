<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="<c:url value="/"/>">GraphQL Shop</a>
    <ul class="navbar-nav">
      <li class="nav-item"><a class="nav-link" href="<c:url value="/users"/>">Users</a></li>
      <li class="nav-item"><a class="nav-link" href="<c:url value="/categories"/>">Categories</a></li>
      <li class="nav-item"><a class="nav-link" href="<c:url value="/products"/>">Products</a></li>
    </ul>
  </div>
</nav>
