package uz.pdp.shopping_web_app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.shopping_web_app.entity.Product;
import uz.pdp.shopping_web_app.repo.ProductRepo;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "productPhoto", value = "/product/photo")
public class ProductPhotoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID id = UUID.fromString(req.getParameter("id"));
        Product product = ProductRepo.findById(id);
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write(product.getPhoto());
    }
}
