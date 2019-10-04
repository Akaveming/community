package io.akave.java.practice.community.service;


import io.akave.java.practice.community.dto.QuestionDTO;
import io.akave.java.practice.community.mapper.QuestionMapper;
import io.akave.java.practice.community.mapper.UserMapper;
import io.akave.java.practice.community.model.Question;
import io.akave.java.practice.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author akave
 */
@Service
public class QuestionService {
    @Autowired
   private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list(Integer index, Integer size){
        List<QuestionDTO> questionDTOs = new ArrayList<>(15);
        Integer offset = (index - 1) * size;
        List<Question> questions = questionMapper.list(offset, size);
        for (Question question : questions) {
           User user = userMapper.findUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOs.add(questionDTO);
        }
        return questionDTOs;
    }
}
