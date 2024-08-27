<%@ page import="uz.pdp.shopping_web_app.repo.BasketRepo" %>
<%@ page import="uz.pdp.shopping_web_app.entity.Basket" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.shopping_web_app.repo.BasketProductRepo" %>
<%@ page import="uz.pdp.shopping_web_app.entity.BasketProduct" %>
<%@ page import="uz.pdp.shopping_web_app.repo.ProductRepo" %>
<%@ page import="uz.pdp.shopping_web_app.entity.Product" %>
<%@ page import="uz.pdp.shopping_web_app.repo.UserRepo" %>
<%@ page import="uz.pdp.shopping_web_app.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 3/31/2024
  Time: 2:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Basket</title>
    <link rel="stylesheet" href="/static/bootstrap.min.css">
</head>
<body>

<%
    Basket basket = BasketRepo.findActiveBasket();
    List<BasketProduct> basketProducts = BasketProductRepo.findAll();
    List<Product> products = ProductRepo.findAll();
    Integer totalPrice = 0;

    for (BasketProduct basketProduct : basketProducts) {
        for (Product product : products) {
            if (basketProduct.getBasketId().equals(basket.getId())) {
                if (basketProduct.getProductId().equals(product.getId())) {
                    totalPrice += product.getPrice() * basketProduct.getAmount();
                }
            }
        }
    }

    User currentUser = UserRepo.getUser(session);
%>


<div class="col-12">

    <div class="row">
        <div class="offset-11 p-3">
            <a class="btn btn-dark" href="http://localhost:8080/">Back Home</a>
        </div>
    </div>
    <hr>

    <div class="row">
        <div class="col-6"><h4 class="text-dark">BASKET ID:<%= basket.getId() %>
        </h4>
        </div>
        <div class="offset-2 col-4">
            <h4 class="text-info ">TOTAL PRICE: <%=totalPrice%>
                <% if (currentUser != null) { %>
                <a class="btn btn-success" href="makeOrders.jsp?id=<%=basket.getId()%>">Order</a>
                <% } else { %>
                <a class="btn btn-success" href="login.jsp">Login</a>
                <% } %>
            </h4>
        </div>
    </div>

    <hr>


    <div class="p-4">
        <table class="table table-striped">
            <thead>
            <tr>

                <th class="w-20">products</th>
                <th class="w-20">price</th>
                <th class=" w-20">amount</th>
                <th class="w-20">total price</th>
                <th class=" w-20">#
                </th>

            </tr>
            </thead>
            <tbody>
            <tr>
                <div>
                    <% for (BasketProduct basketProduct : basketProducts) { %>
                    <% for (Product product : products) { %>
                    <% if (basket.getId().equals(basketProduct.getBasketId()) &&
                            basketProduct.getProductId().equals(product.getId())) { %>
                    <tr>
                        <td><%= product.getName() %>
                        </td>
                        <td><%= product.getPrice()%>
                        </td>
                        <td><%= basketProduct.getAmount()%>
                        </td>
                        <td><%= product.getPrice() * basketProduct.getAmount() %>
                        </td>
                        <td>
                            <input name="productName" type="hidden" disabled value="<%= product.getName()%>">
                            <input name="productAmount" type="hidden" disabled value="<%= basketProduct.getAmount()%>">
                            <input name="price" type="hidden" disabled value="<%= product.getPrice()%>">
                            <a class="btn btn-danger"
                               href="http://localhost:8080/basketProduct/cancel?id=<%=product.getId()%>">Cancel</a>
                        </td>


                    </tr>
                    <% } %>
                    <% } %>
                    <% } %>
                </div>

            </tbody>

        </table>

    </div>

</div>


</body>
</html>


