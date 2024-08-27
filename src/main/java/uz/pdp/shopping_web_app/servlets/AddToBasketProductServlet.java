package uz.pdp.shopping_web_app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.shopping_web_app.entity.Basket;
import uz.pdp.shopping_web_app.entity.BasketProduct;
import uz.pdp.shopping_web_app.repo.BasketProductRepo;
import uz.pdp.shopping_web_app.repo.BasketRepo;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "addToBasket", value = "/basketProduct/add")
public class AddToBasketProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID productId = UUID.fromString(req.getParameter("id"));
        int amount = Integer.parseInt(req.getParameter("amount"));
        BasketProduct basketProduct = new BasketProduct();
        basketProduct.setAmount(amount);
        basketProduct.setProductId(productId);
        Basket basket = BasketRepo.findActiveBasket();
        basketProduct.setBasketId(basket.getId());
        BasketProductRepo.save(basketProduct);
        resp.sendRedirect("/index.jsp");
    }

}
