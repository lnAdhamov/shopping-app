package uz.pdp.shopping_web_app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.shopping_web_app.entity.User;
import uz.pdp.shopping_web_app.repo.UserRepo;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "Auth", value = "/auth/login")
public class AuthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        Optional<User> userOptional = UserRepo.findByName(name);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if(user.getPassword().equals(password)){
                user.authenticate(resp,req);
            }else {
                resp.sendRedirect("/login.jsp");
            }
        } else {
            resp.sendRedirect("/login.jsp");
        }


    }
}
