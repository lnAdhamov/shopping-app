package uz.pdp.shopping_web_app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.shopping_web_app.repo.OrderProductRepo;
import uz.pdp.shopping_web_app.repo.OrdersRepo;

import java.io.IOException;

@WebServlet(name = "deliverOrder",value = "/orders/deliver")
public class DeliverOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        OrdersRepo.deliverById(id);
        OrderProductRepo.clearDeliveredOrderProductsByOrderId(id);
        resp.sendRedirect("/admin/orders.jsp");
    }
}
