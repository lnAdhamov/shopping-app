package uz.pdp.shopping_web_app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.shopping_web_app.repo.OrderProductRepo;
import uz.pdp.shopping_web_app.repo.OrdersRepo;

import java.io.IOException;

@WebServlet(name = "deleteOrder", value = "/orders/delete")
public class DeleteOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        OrderProductRepo.clearDeliveredOrderProductsByOrderId(id);
        OrdersRepo.deleteById(id);
        resp.sendRedirect("/admin/orders.jsp");
    }
}
