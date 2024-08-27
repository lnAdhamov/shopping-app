package uz.pdp.shopping_web_app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.shopping_web_app.entity.Category;
import uz.pdp.shopping_web_app.repo.CategoryRepo;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "EditCategory", value = "/category/edit")
public class EditCategoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        UUID id = UUID.fromString(req.getParameter("id"));
        CategoryRepo.edit(new Category(id,name));
        resp.sendRedirect("/admin/category.jsp");
    }
}
