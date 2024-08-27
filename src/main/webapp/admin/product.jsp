<%@ page import="uz.pdp.shopping_web_app.entity.Product" %>
<%@ page import="uz.pdp.shopping_web_app.repo.ProductRepo" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 3/31/2024
  Time: 2:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product</title>
    <link rel="stylesheet" href="/static/bootstrap.min.css">
</head>
<body>


<%
    List<Product> products = ProductRepo.findAll();
%>

<div class="row">
    <div class="col-3 border-right">
        <ul class="list-group p-3">
            <a href="category.jsp">
                <li class="list-group-item text-dark my-1">Categories</li>
            </a>
            <a href="product.jsp">
                <li class="list-group-item bg-dark text-white my-1">Products</li>
            </a>
            <a href="orders.jsp">
                <li class="list-group-item text-dark my-1">Orders</li>
            </a>
        </ul>
    </div>
    <div class="col-9">

        <div class="row">
            <h3 class="p-3">Products</h3>
            <div class="offset-7 p-3">
                <a class="btn btn-dark" href="http://localhost:8080/">Back Home</a>
                <a class="btn btn-dark" href="addProduct.jsp">Add Product</a>
            </div>
        </div>

        <hr>

        <div class="p-4">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>price</th>
                    <th>Category</th>
                    <th>#</th>
                </tr>
                </thead>
                <tbody>
                <%for (Product product : products) {%>
                <tr>
                    <td><%=product.getId()%>
                    </td>
                    <td><%=product.getName()%>
                    </td>
                    <td><%=product.getPrice()%>
                    </td>
                    <td><%=product.categoryName() %>
                    </td>
                    <td>
                        <a href="editProduct.jsp?id=<%=product.getId()%>" class="btn btn-info">edit</a>
                        <a href="http://localhost:8080/product/delete?id=<%=product.getId()%>"
                           class="btn btn-danger">delete
                        </a>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>

</div>
</body>
</html>
