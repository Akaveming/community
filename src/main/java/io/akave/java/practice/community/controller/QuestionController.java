package io.akave.java.practice.community.controller;

import io.akave.java.practice.community.dto.QuestionDTO;
import io.akave.java.practice.community.model.User;
import io.akave.java.practice.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author akave
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(
            @PathVariable(name = "id") Integer id,
            Model model,
            HttpServletRequest request
            ) {

        QuestionDTO questionDTO = questionService.findQuestionById(id);

        model.addAttribute("question", questionDTO);
        return "question";
    }
}
