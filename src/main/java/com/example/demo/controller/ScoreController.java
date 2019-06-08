package com.example.demo.controller;

import com.example.demo.dao.ScoreRepository;
import com.example.demo.entity.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
/*
@Controller
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    private ScoreRepository scoreRepository;
    @RequestMapping("/table")
    public ModelAndView table(){
        List<Score> scoreList = scoreRepository.findScoreByStu_idAndTitle(null,null);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("score/table");
        mv.addObject("scoreList",scoreList);
        return mv;
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<Score> list(
            @RequestParam(value="stuId",required = false) Integer stuId,
            @RequestParam(value="title",required = false) String title){
        List<Score> scoreList = scoreRepository.findScoreByStu_idAndTitle(stuId,title);
        return scoreList;
    }
}
*/