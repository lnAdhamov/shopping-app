<%@ page import="java.util.UUID" %><%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 3/31/2024
  Time: 9:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delivering</title>
    <link rel="stylesheet" href="/static/bootstrap.min.css">
</head>
<body>

<%
    Integer id = Integer.parseInt(request.getParameter("id"));
%>

<div class="row mt-4">
    <div class="col-4 offset-4">
        <div class="card p-3">
            <form action="http://localhost:8080/orders/deliver?id=<%= id %>" method="POST">
                <h3>Deliver the ID: <%=id%> Order</h3>
                <button class="btn btn-dark w-100" autofocus type="submit">Deliver</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
