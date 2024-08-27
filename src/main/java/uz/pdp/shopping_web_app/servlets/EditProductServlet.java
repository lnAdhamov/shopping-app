package uz.pdp.shopping_web_app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import uz.pdp.shopping_web_app.entity.Product;
import uz.pdp.shopping_web_app.repo.ProductRepo;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "editProduct", value = "/product/edit")
@MultipartConfig
public class EditProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        Integer price = Integer.parseInt(req.getParameter("price"));
        UUID categoryId = UUID.fromString(req.getParameter("categoryId"));
        UUID id = UUID.fromString(req.getParameter("id"));
        Part photo = req.getPart("photo");
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategoryId(categoryId);
        product.setId(id);
        product.setPhoto(photo.getInputStream().readAllBytes());
        ProductRepo.edit(product);
        resp.sendRedirect("/admin/product.jsp");
    }
}
