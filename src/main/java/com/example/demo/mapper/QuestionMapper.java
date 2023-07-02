package com.example.demo.mapper;

import com.example.demo.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface QuestionMapper {
    List<Question> selectQuesByKeys(@Param("keys")String keys);

    Question selectById(Integer id);

    int insertQuestion(Question record);

    int updateQuestionById(Question question);




}
