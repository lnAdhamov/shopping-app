package uz.pdp.shopping_web_app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.shopping_web_app.repo.OrdersRepo;

import java.io.IOException;

@WebServlet(name = "startOrder", value = "/orders/start")
public class StartOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        OrdersRepo.setStatusById(id);
        resp.sendRedirect("/admin/orders.jsp");
    }
}
