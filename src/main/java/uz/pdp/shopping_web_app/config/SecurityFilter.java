package uz.pdp.shopping_web_app.config;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import uz.pdp.shopping_web_app.entity.User;
import uz.pdp.shopping_web_app.repo.UserRepo;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@WebFilter(urlPatterns = "/admin/*")
public class SecurityFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        Object currentUser = session.getAttribute("currentUser");

        if (currentUser == null) {
            Optional<Cookie> cookieOptional = Arrays.stream(req.getCookies()).filter(cookie -> cookie.getName().equals("userId")).findFirst();
            if (cookieOptional.isPresent()) {
                Cookie cookie = cookieOptional.get();
                UUID userId = UUID.fromString(cookie.getValue());
                Optional<User> userOptional = UserRepo.findById(userId);
                if (userOptional.isPresent()) {
                    session.setAttribute("currentUser",userOptional.get());
                    resp.sendRedirect("/admin/admin.jsp");
                } else {
                    resp.sendRedirect("/404");
                }
            } else {
                resp.sendRedirect("/404");
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }
}
