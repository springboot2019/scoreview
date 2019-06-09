package com.example.demo.controller;

import com.example.demo.dao.ScoreRepository;
import com.example.demo.entity.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class ScoreController {
    @Autowired
    private ScoreRepository scoreRepository;
    /*
    @RequestMapping("/table")
    public ModelAndView table(){
        List<Score> scoreList = scoreRepository.findScoreByStu_idAndTitle(null,null);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("score/table");
        mv.addObject("scoreList",scoreList);
        return mv;
    }*/
    //获取所有学生信息
    @PostMapping(value = "/score/table")
    @ResponseBody
    public Map getStudents(@RequestParam Integer stu_id){
        List<Score> scoreList = scoreRepository.findScoreByStu_id(stu_id);
        Map resultMap=new HashMap();
        resultMap.put("total",scoreList.size());
        resultMap.put("rows",scoreList);
        return resultMap;
    }
/*
    @RequestMapping("/list")
    @ResponseBody
    public List<Score> list(
            @RequestParam(value="stuId",required = false) Integer stuId,
            @RequestParam(value="title",required = false) String title){
        List<Score> scoreList = scoreRepository.findScoreByStu_idAndTitle(stuId,title);
        return scoreList;
    }*/
}
