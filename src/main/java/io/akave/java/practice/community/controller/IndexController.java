package io.akave.java.practice.community.controller;

import io.akave.java.practice.community.dto.PaginationDTO;
import io.akave.java.practice.community.dto.QuestionDTO;
import io.akave.java.practice.community.mapper.QuestionMapper;
import io.akave.java.practice.community.model.QuestionExample;
import io.akave.java.practice.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;
    /**
     * 首页
     * @return
     */
    @GetMapping("/")
    public String index(
            Model model,
            @RequestParam(name = "index",defaultValue = "1") Integer index,
            @RequestParam(name = "size",defaultValue = "12") Integer size
    ){
        QuestionExample questionExample = new QuestionExample();
        Integer totalCount = Math.toIntExact(questionMapper.countByExample(questionExample));

        int total;
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
