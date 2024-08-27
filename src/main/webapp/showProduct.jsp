<%@ page import="uz.pdp.shopping_web_app.repo.CategoryRepo" %>
<%@ page import="uz.pdp.shopping_web_app.entity.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.UUID" %>
<%@ page import="uz.pdp.shopping_web_app.repo.ProductRepo" %>
<%@ page import="uz.pdp.shopping_web_app.entity.Product" %>
<%@ page import="uz.pdp.shopping_web_app.repo.UserRepo" %>
<%@ page import="uz.pdp.shopping_web_app.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 3/31/2024
  Time: 8:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
    <link rel="stylesheet" href="/static/bootstrap.min.css">
</head>
<body>
<%
    List<Category> categories = CategoryRepo.findAll();
    List<Product> products = ProductRepo.findAll();
    UUID id = UUID.fromString(request.getParameter("id"));
    User currentUser = UserRepo.getUser(session);
%>


<nav class="navbar bg-body-tertiary bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand text-light">G-35 Market</a>
        <div>
            <%if (currentUser == null) {%>
            <a class="btn btn-outline-light" href="login.jsp">Login</a>
            <%} else if (currentUser != null && currentUser.getRole().equals("client")) { %>
            <a class="btn btn-outline-danger" href="/auth/logout">Log Out</a>
            <%}%>
            <a class="btn btn-outline-light" href="basket.jsp">Basket 🛒</a>
        </div>
    </div>
</nav>

<div class="row">
    <div class="col-2 border-right">
        <a class="navbar-brand text-dark offset-1 mt-2">Categories</a>
        <ul class="list-group p-3">
            <% for (Category category : categories) { %>
            <a href="showProduct.jsp?id=<%=category.getId()%>">
                <% if (category.getId().equals(id)) { %>
                <li class="list-group-item text-light my-1 bg-dark"><%=category.getName()%>
                </li>
                <% } else { %>
                <li class="list-group-item text-dark my-1"><%=category.getName()%>
                </li>
                <% } %>
            </a>
            <% } %>
        </ul>
    </div>

    <div class="col-10">
        <% for (Product product : products) { %>
        <% if (product.getCategoryId().equals(id)) { %>
        <div class="card">
            <img src="/product/photo?id=<%= product.getId() %>" class="card-img-top w-25 h-25" alt="Product Photo">
            <div class="card-body">
                <div>
                    <h5><%= product.getName()%>
                    </h5>
                </div>
            </div>
            <div class="card-footer">
                <h5>
                    <small class="text-dark">Product Price: <%=product.getPrice()%> sum</small>
                    <a href="addToBasket.jsp?id=<%=product.getId()%>" class="btn btn-outline-dark">Add to 🛒</a>
                </h5>
            </div>
        </div>
        <% } %>
        <% } %>

    </div>
</div>
</body>
</html>
