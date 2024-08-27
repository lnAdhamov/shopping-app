<%@ page import="uz.pdp.shopping_web_app.repo.ProductRepo" %>
<%@ page import="uz.pdp.shopping_web_app.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.UUID" %><%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 4/1/2024
  Time: 10:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add to Basket</title>
    <link rel="stylesheet" href="/static/bootstrap.min.css">
</head>
<body>

<%
    UUID id = UUID.fromString(request.getParameter("id"));
%>


<div class="row mt-4">
    <div class="col-4 offset-4">
        Add To Basket
        <div class="card p-3">
            <form action="http://localhost:8080/basketProduct/add" method="POST">
                <input name="amount" autofocus class="form-control my-3" type="number" placeholder="enter amount">
                <input name="id" value="<%=id%>" type="hidden">
                <button class="btn btn-dark" type="submit">Add</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
