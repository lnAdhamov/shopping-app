<%@ page import="java.util.UUID" %>
<%@ page import="uz.pdp.shopping_web_app.repo.CategoryRepo" %>
<%@ page import="uz.pdp.shopping_web_app.entity.Category" %><%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 3/31/2024
  Time: 5:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EditCategory</title>
    <link rel="stylesheet" href="/static/bootstrap.min.css">
</head>
<body>

<%
    UUID id = UUID.fromString(request.getParameter("id"));
    Category category = CategoryRepo.findById(id);
%>

<div class="row mt-4">
    <div class="col-4 offset-4">
        <div class="card p-3">
            <form action="http://localhost:8080/category/edit" method="POST">
                <input type=hidden name="id" value="<%=category.getId()%>">
                <input name="name" class="form-control my-3" value=<%=category.getName()%>  type="text"
                       placeholder="enter name">
                <button class="btn btn-dark w-100" type="submit">edit</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
