package com.example.demo.service;

import com.example.demo.entity.Question;
import com.example.demo.mapper.QuestionMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.List;
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    //每页显示多条数据
    private  Integer pageSize = 30;

    public PageInfo<Question> selectByKeys(String keys, Integer pageNum){
        PageHelper.startPage(pageNum, pageSize);
        List<Question> questions = questionMapper.selectQuesByKeys(keys);
        PageInfo<Question> questionPageInfo = new PageInfo<>(questions);
        return questionPageInfo;
    }

    public Question selectById(Integer id) {
        Question question = questionMapper.selectById(id);
        return question;
    }

    public void insert(Question question) {
        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = userDetail.getUsername();
        question.setQuestioner(name);
        questionMapper.insertQuestion(question);
    }

    public void update(Question question) {
        questionMapper.updateQuestionById(question);
    }




}
