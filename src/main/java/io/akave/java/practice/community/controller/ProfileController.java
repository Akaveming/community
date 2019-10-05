package io.akave.java.practice.community.controller;

import io.akave.java.practice.community.dto.PaginationDTO;
import io.akave.java.practice.community.dto.QuestionDTO;
import io.akave.java.practice.community.mapper.QuestionMapper;
import io.akave.java.practice.community.model.User;
import io.akave.java.practice.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author akave
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionMapper questionMapper;

    @RequestMapping(value = "/profile/{action}" )
    public String profile(
            @PathVariable(name = "action") String action,
            Model model,
            HttpServletRequest request,
            @RequestParam(name = "index",defaultValue = "1") Integer index,
            @RequestParam(name = "size",defaultValue = "12") Integer size
            ) {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            return "redirect:/";
        }

        if ("questions".equals(action) || action == null || "".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的问题");
        }

        if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }

        Integer totalCount = questionMapper.countByUsrId(user.getId());
        int total = 0;
        if (totalCount % size == 0) {
            total = totalCount / size;
        } else {
            total = totalCount / size + 1;
        }

        PaginationDTO paginationDTO = new PaginationDTO(total, index);
        model.addAttribute("pagination", paginationDTO);

        List<QuestionDTO> questionDTOs = questionService.list(user.getId(),index, size);
        model.addAttribute("questions", questionDTOs);

        return "profile";
    }
}
