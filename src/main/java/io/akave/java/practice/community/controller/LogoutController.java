package io.akave.java.practice.community.controller;

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
        request.getSession().removeAttribute("user");
        Cookie token = new Cookie("token", "");
        token.setMaxAge(0);
        response.addCookie(token);
        return "redirect:/";
    }
}
