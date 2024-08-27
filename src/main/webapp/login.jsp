<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 4/12/2024
  Time: 5:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="static/bootstrap.min.css">
</head>
<body>

<div class="row">
    <div class="col-4 offset-4 p-3">
        <div class="card">
            <div class="card-header"></div>
            <div class="card-body"></div>
            <form action="/auth/login" method="POST">
                <div>
                    <input type="text" placeholder="name" name="name" class="form-control my-2 mx-2">
                </div>
                <div>
                    <input type="password" placeholder="password" name="password" class="form-control my-2 mx-2">
                </div>
                <div>
                    <labal class="form-check-label my-2 mx-2">
                          remember me
                        <input name="rememberMe" class="form-check my-2 mx-2" type="checkbox">
                    </labal>
                </div>
                <button class="btn btn-outline-dark my-2 mx-2">Sign In</button>
            </form>
        </div>
    </div>
</div>


</body>
</html>
