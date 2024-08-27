<%@ page import="java.util.UUID" %><%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 4/1/2024
  Time: 3:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Make Order</title>
    <link rel="stylesheet" href="static/bootstrap.min.css">
</head>
<body>


<div class="row mt-4">
    <div class="col-4 offset-4">
        <div class="card p-3">
            <form action="http://localhost:8080/orders/make?id=<%=UUID.fromString(request.getParameter("id"))%>"
                  method="POST">
                <div>
                    <h5>Press "Confirm" If You Sure To Make this Order</h5>
                    <button class="btn btn-dark w-100  my-4" autofocus type="submit">Confirm</button>
                </div>
            </form>
        </div>
    </div>
</div>


</body>
</html>
