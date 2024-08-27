package uz.pdp.shopping_web_app.entity;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Objects;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private UUID id;
    private String phone;
    private String password;
    private String role;

    @SneakyThrows
    public void authenticate(HttpServletResponse resp, HttpServletRequest req) {
        String rememberMe = req.getParameter("rememberMe");
        HttpSession session = req.getSession();
        session.setAttribute("currentUser", this);
        if (Objects.equals(rememberMe, "on")) {
            Cookie cookie = new Cookie("userId", this.id.toString());
            cookie.setPath("/");
            cookie.setSecure(false);
            cookie.setMaxAge(60 * 60);
            resp.addCookie(cookie);
        } else {
            if (this.getRole().equals("admin")) {
                resp.sendRedirect("/");
            } else if (this.getRole().equals("client")) {
                resp.sendRedirect("/");
            }
        }
    }
}
