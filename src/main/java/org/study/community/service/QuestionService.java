package org.study.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.community.dto.PaginationDTO;
import org.study.community.dto.QuestionDTO;
import org.study.community.mapper.QuestionMapper;
import org.study.community.mapper.UserMapper;
import org.study.community.model.Question;
import org.study.community.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangkai
 * @description TODO
 * @date 2019/7/5 13:40
 **/
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        int totalCount = questionMapper.count();

        PaginationDTO pagination = new PaginationDTO();
        pagination.setPaginationDTO(page,size,totalCount);

        //将输入的页码调整到数据允许的页码内
        page = pagination.getCurrentPage();

        int offset = size * (page - 1);
        List<Question> quesList = questionMapper.list(offset,size);
        List<QuestionDTO> quesDTOList = new ArrayList<>();
        for(Question question : quesList){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            quesDTOList.add(questionDTO);
        }

        pagination.setQuestions(quesDTOList);
        return pagination;
    }
}
