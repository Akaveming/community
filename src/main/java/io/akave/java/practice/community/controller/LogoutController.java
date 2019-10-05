package io.akave.java.practice.community.controller;

import io.akave.java.practice.community.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author akave
 */
@Controller
public class LogoutController {
    @RequestMapping("/logout")
    public String logout(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        User user = (User) request.getSession().getAttribute("user");
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    if (user.getToken().equals(cookie.getValue())) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                        return "redirect:/";
                    }
                    break;
                }
            }
        }
        return "redirect:/";
    }
}
