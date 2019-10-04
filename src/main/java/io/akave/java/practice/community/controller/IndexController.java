package io.akave.java.practice.community.controller;

import io.akave.java.practice.community.dto.PaginationDTO;
import io.akave.java.practice.community.dto.QuestionDTO;
import io.akave.java.practice.community.mapper.QuestionMapper;
import io.akave.java.practice.community.mapper.UserMapper;
import io.akave.java.practice.community.model.User;
import io.akave.java.practice.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;
    /**
     * 首页
     * @param request
     * @return
     */
    @GetMapping("/")
    public String index(
            HttpServletRequest request,
            Model model,
            @RequestParam(name = "index",defaultValue = "1") Integer index,
            @RequestParam(name = "size",defaultValue = "12") Integer size
    ){
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("token".equals(name)) {
                    String token = cookie.getValue();
                    User user = userMapper.findUserByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                }
            }
        }

        Integer totalCount = questionMapper.count();
        int total = 0;
        if (totalCount % size == 0) {
            total = totalCount / size;
        } else {
            total = totalCount / size + 1;
        }

        if (index < 0)
            index = 1;
        if (index > total)
            index = total;
        PaginationDTO paginationDTO = new PaginationDTO(total, index);
        model.addAttribute("pagination", paginationDTO);

        List<QuestionDTO> questionDTOs = questionService.list(index, size);
        model.addAttribute("questions", questionDTOs);

        return "index";
    }
}
