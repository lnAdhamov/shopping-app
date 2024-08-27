<%@ page import="uz.pdp.shopping_web_app.repo.CategoryRepo" %>
<%@ page import="uz.pdp.shopping_web_app.entity.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.shopping_web_app.repo.UserRepo" %>
<%@ page import="uz.pdp.shopping_web_app.entity.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>G-35 Market</title>
    <link rel="stylesheet" href="static/bootstrap.min.css ">
</head>
<body>

<%
    List<Category> categories = CategoryRepo.findAll();
    User currentUser = UserRepo.getUser(session);
%>

<nav class="navbar bg-body-tertiary bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand text-light">G-35 Market</a>
        <div>
            <%if (currentUser == null) {%>
            <a class="btn btn-outline-light" href="login.jsp">Login</a>
            <%} else if (currentUser != null && currentUser.getRole().equals("admin")) { %>
            <a class="btn btn-outline-success" href="admin/admin.jsp">Admin</a>
            <a class="btn btn-outline-danger" href="/auth/logout">Log Out</a>
            <%} else if (currentUser != null && currentUser.getRole().equals("client")) { %>
            <a class="btn btn-outline-danger" href="/auth/logout">Log Out</a>
            <% } %>
            <a class="btn btn-outline-light" href="basket.jsp">Basket 🛒</a>
        </div>
    </div>
</nav>

<div class="row">
    <div class="col-2 border-right">
        <a class="navbar-brand text-dark offset-1 mt-2">Categories</a>
        <ul class="list-group p-3">
            <% for
            (Category category : categories) { %>
            <a href="showProduct.jsp?id=<%=category.getId()%>">
                <li class="list-group-item text-dark my-1"><%=category.getName()%>
                </li>
            </a>
            <% } %>
        </ul>
    </div>
    <div class="col-9"></div>
</div>


</body>
</html>