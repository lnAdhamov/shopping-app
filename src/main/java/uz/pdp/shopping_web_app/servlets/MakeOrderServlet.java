package uz.pdp.shopping_web_app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uz.pdp.shopping_web_app.entity.BasketProduct;
import uz.pdp.shopping_web_app.entity.User;
import uz.pdp.shopping_web_app.repo.*;


import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "makeOrder", value = "/orders/make")
public class MakeOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object currentUser = session.getAttribute("currentUser");
        User currentUser1 = (User) currentUser;
        UUID id = UUID.fromString(req.getParameter("id"));
        List<BasketProduct> basketProducts = OrderProductRepo.readProductsFromBasketProducts(id);
        Integer orderId = OrdersRepo.newOrder(currentUser1);
        OrderProductRepo.putProductsFromBasketProduct(basketProducts, orderId);
                BasketProductRepo.clearOldBasketProducts(id);
        resp.sendRedirect("/basket.jsp");
    }
}
