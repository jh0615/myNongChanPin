package com.example.demo.service;

import com.example.demo.entity.Expert;
import com.example.demo.mapper.ExpertMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExpertService {
    @Autowired
    private ExpertMapper expertMapper;

    //每页显示多条数据
    private  Integer pageSize = 30;

    public PageInfo<Expert> selectByKeys(String keys, Integer pageNum){
        PageHelper.startPage(pageNum, pageSize);
        List<Expert> experts = expertMapper.selectExpertByKeys(keys);
        PageInfo<Expert> expertPageInfo = new PageInfo<>(experts);
        return expertPageInfo;
    }
}
