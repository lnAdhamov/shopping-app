<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 3/31/2024
  Time: 4:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddCategory</title>
    <link rel="stylesheet" href=/static/bootstrap.min.css>
</head>
<body>

<div class="row mt-4">
    <div class="col-4 offset-4">
        <div class="card p-3">
            <form action="http://localhost:8080/category/add" method="POST">
                <input name="name" class="form-control my-3" type="text" placeholder="enter name">
                <button class="btn btn-dark w-100" type="submit">save</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
