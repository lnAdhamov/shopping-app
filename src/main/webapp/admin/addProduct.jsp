<%@ page import="uz.pdp.shopping_web_app.repo.CategoryRepo" %>
<%@ page import="uz.pdp.shopping_web_app.entity.Category" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 3/31/2024
  Time: 5:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddProduct</title>
    <link rel="stylesheet" , href="/static/bootstrap.min.css">
</head>
<body>

<%
    List<Category> categories = CategoryRepo.findAll();
%>

<div class="row mt-4">
    <div class="col-4 offset-4">
        <div class="card p-3">
            <form enctype="multipart/form-data" action="http://localhost:8080/product/add" method="POST">
                <input name="photo" type="file" class="form-control-file">
                <input name="name" class="form-control my-3" type="text" placeholder="enter name">
                <input name="price" class="form-control my-3" type="number" placeholder="enter price">
                <select name="categoryId" class="form-control ">
                    <option selected disabled>Select Category</option>
                    <% for (Category category : categories) { %>
                    <option value="<%= category.getId()%>"><%=category.getName()%>
                    </option>
                    <% } %>
                </select>
                <button class="btn btn-dark w-100 mt-3" type="submit">save</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
