package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.common.StatusCode;
import com.example.demo.entity.Expert;
import com.example.demo.entity.Question;
import com.example.demo.service.ExpertService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.QuestionService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ExpertService expertService;





    //分页查询所有问题
    @GetMapping("/findAllQues/{pageNum}")
    public Result<PageInfo<Question>> findAllQues(@PathVariable Integer pageNum) {
        PageInfo<Question>  questionPageInfo = questionService.selectByKeys(null,pageNum);
        return new Result(true, StatusCode.OK, "查询成功", questionPageInfo);
    }

    //分页条件查询问题
    @GetMapping("/findQuesByKeys/{keys}/{pageNum}")
    public Result<PageInfo<Question>> findQuesByKeys(@PathVariable("keys") String keys,@PathVariable Integer pageNum) {
        PageInfo<Question>  questionPageInfo = questionService.selectByKeys(keys,pageNum);
        return new Result(true, StatusCode.OK, "查询成功", questionPageInfo);
    }

    //分页查询所有专家
    @GetMapping("/findAllExpert/{pageNum}")
    public Result<PageInfo<Expert>> findAllExpert(@PathVariable Integer pageNum) {
        PageInfo<Expert> expertPageInfo = expertService.selectByKeys(null, pageNum);
        return new Result<PageInfo<Expert>>(true, StatusCode.OK, "查询成功", expertPageInfo);
    }

    //分页条件查询专家
    @GetMapping("/findExpertByKeys/{keys}/{pageNum}")
    public Result<PageInfo<Expert>> findExpertByKeys(@PathVariable("keys") String keys,@PathVariable Integer pageNum) {
        PageInfo<Expert> expertPageInfo = expertService.selectByKeys(keys,pageNum);
        return new Result<PageInfo<Expert>>(true, StatusCode.OK, "查询成功", expertPageInfo);
    }

    //根据ID查询问题内容
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable("id") Integer id) {
        Question question = questionService.selectById(id);
        return new Result(true, StatusCode.OK, "查询成功", question);
    }

    //添加问题
    @PostMapping("/add")
    public Result add(@Valid @RequestBody Question question, BindingResult bindingResult) {
        //检查项目
        if (bindingResult.hasErrors()) {
            return new Result<String>(false, StatusCode.ERROR, "添加失败");
        }
        questionService.insert(question);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    //根据id修改询问情报
    @PutMapping("/update")
    public Result update(@Valid @RequestBody Question question, BindingResult bindingResult) {
        //检查项目
        if (bindingResult.hasErrors()) {
            return new Result<String>(false, StatusCode.ERROR, "修改失败");
        }
        questionService.update(question);
        return new Result(true, StatusCode.OK, "修改成功");
    }



}
