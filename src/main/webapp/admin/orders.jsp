<%@ page import="uz.pdp.shopping_web_app.repo.OrdersRepo" %>
<%@ page import="uz.pdp.shopping_web_app.entity.Orders" %>
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
    <title>Orders</title>
    <link rel="stylesheet" href="/static/bootstrap.min.css">
</head>
<body>

<%
    List<Orders> orders = OrdersRepo.findAll();
%>

<div class="row">
    <div class="col-3 border-right">
        <ul class="list-group p-3">
            <a href="category.jsp">
                <li class="list-group-item text-dark my-1">Categories</li>
            </a>
            <a href="product.jsp">
                <li class="list-group-item text-dark my-1">Products</li>
            </a>
            <a href="orders.jsp">
                <li class="list-group-item bg-dark text-white my-1">Orders</li>
            </a>
        </ul>
    </div>
    <div class="col-9">

        <div class="row">
            <h3 class="p-3">Orders</h3>
            <div class="offset-9 p-3">
                <a class="btn btn-dark" href="http://localhost:8080/">Back Home</a>
            </div>
        </div>

        <hr>

        <div class="p-4">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>id</th>
                    <th>date</th>
                    <th>status</th>
                    <th>user id</th>
                    <th>#</th>
                </tr>
                </thead>
                <tbody>
                <%for (Orders order : orders) {%>
                <tr>
                    <td><%=order.getId()%>
                    </td>
                    <td><%=order.formatDate()%>
                    </td>
                    <%if (order.isDelivered()) {%>
                    <td class="bg-success"><%=order.checkDeliver()%>
                    </td>
                    <% } else if (order.isStatus()) { %>
                    <td class="bg-warning"><%=order.checkStatus()%>
                    </td>
                    <% } else { %>
                    <td class="bg-info"><%=order.checkStatus()%>
                    </td>
                    <% } %>
                    <td><%=order.getUserId()%>
                    </td>
                    <td>
                        <% if (order.isDelivered()) { %>
                        <a href="http://localhost:8080/orders/delete?id=<%=order.getId()%>"
                           class="btn btn-danger">delete
                        </a>
                        <% } else if (order.isStatus()) { %>
                        <a href="http://localhost:8080/orders/cancel?id=<%=order.getId()%>"
                           class="btn btn-danger">Cancel
                        </a>
                        <a href="deliverOrder.jsp?id=<%= order.getId()%>" class="btn btn-warning">Deliver</a>
                        <% } else { %>
                        <a href="http://localhost:8080/orders/cancel?id=<%=order.getId()%>"
                           class="btn btn-danger">Cancel
                        </a>
                        <a href="http://localhost:8080/orders/start?id=<%=order.getId()%>"
                           class="btn btn-info">Start</a>
                        <% } %>
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
