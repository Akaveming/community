package io.akave.java.practice.community.service;


import io.akave.java.practice.community.dto.QuestionDTO;
import io.akave.java.practice.community.mapper.QuestionMapper;
import io.akave.java.practice.community.mapper.UserMapper;
import io.akave.java.practice.community.model.Question;
import io.akave.java.practice.community.model.QuestionExample;
import io.akave.java.practice.community.model.User;
import org.apache.ibatis.session.RowBounds;
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
//        List<Question> questions = questionMapper.list(offset, size);
        QuestionExample questionExample = new QuestionExample();
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample,new RowBounds(offset, size));
        for (Question question : questions) {
           User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOs.add(questionDTO);
        }
        return questionDTOs;
    }

    public List<QuestionDTO> list(Integer userId, Integer index, Integer size) {
        List<QuestionDTO> questionDTOs = new ArrayList<>(15);
        Integer offset = (index - 1) * size;
//        List<Question> questions = questionMapper.listByUserId(userId,offset, size);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample,new RowBounds(offset, size));
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOs.add(questionDTO);
        }
        return questionDTOs;
    }

    public QuestionDTO findQuestionById(Integer id) {
        QuestionDTO questionDTO = new QuestionDTO();
        Question question = questionMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(question,questionDTO);

        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);

        return questionDTO;
    }

    public void  createOrUpdate(Integer id, Question question) {
        if (id == null) {
            //创建问题
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        } else {
            //更新问题
            Question updateQuestion = new Question();
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setTag(question.getTag());
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setDescription(question.getDescription());
            question.setId(id);
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andIdEqualTo(id);
            questionMapper.updateByExampleSelective(updateQuestion, questionExample);
        }
    }
}
