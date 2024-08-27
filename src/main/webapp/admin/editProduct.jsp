<%@ page import="uz.pdp.shopping_web_app.repo.ProductRepo" %>
<%@ page import="uz.pdp.shopping_web_app.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.UUID" %>
<%@ page import="uz.pdp.shopping_web_app.entity.Category" %>
<%@ page import="uz.pdp.shopping_web_app.repo.CategoryRepo" %><%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 3/31/2024
  Time: 5:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EditProduct</title>
    <link rel="stylesheet" href="/static/bootstrap.min.css">
</head>
<body>

<%
    UUID id = UUID.fromString(request.getParameter("id"));
    List<Category> categories = CategoryRepo.findAll();
    Product product = ProductRepo.findById(id);
    Category category1 = product.findCategory();
%>

<div class="row mt-4">
    <div class="col-4 offset-4">
        <div class="card p-3">
            <form enctype="multipart/form-data" action="http://localhost:8080/product/edit?id=<%= product.getId() %>"
                  method="POST">
                <input name="photo" type="file" value="" class="form-control-file">
                <input name="name" value="<%=product.getName()%>" class="form-control my-3" type="text"
                       placeholder="enter name">
                <input name="price" value="<%=product.getPrice()%>" class="form-control my-3" type="number"
                       placeholder="enter price">
                <select name="categoryId" class="form-control ">
                    <option selected disabled>Select Category</option>
                    <% for (Category category : categories) { %>
                    <% if (category.getId().equals(category1.getId())) { %>
                    <option selected value="<%= category.getId()%>"><%=category.getName()%>
                    </option>
                    <% } else { %>
                    <option value="<%= category.getId()%>"><%=category.getName()%>
                    </option>
                    <% } %>
                    <% } %>
                </select>
                <button class="btn btn-dark w-100 mt-3" type="submit">save</button>
            </form>
        </div>
    </div>
</div>


</body>
</html>
