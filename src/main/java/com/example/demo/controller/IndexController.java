package com.example.demo.controller;

import com.example.demo.dao.CommentRepository;
import com.example.demo.dao.ScoreRepository;
import com.example.demo.dao.StudentRepository;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Score;
import com.example.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@ResponseBody
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CommentRepository commentRepository;

    /*
    @RequestMapping("/test")
    public ModelAndView showIndex(
            @RequestParam(value="stuId",required = false) Integer stuId
    )

    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("test");
        List<Score> scoreList = scoreRepository.findScoreByStu_id(stuId);
        List<Comment> commentList = commentRepository.findCommentByStu_id(stuId);
        Student student = studentRepository.getOne(stuId);
        //错误处理
        if(scoreList.size()==0||commentList.size()==0||student==null){
            mv.addObject("errors","某项记录返回为空");
            return mv;
        }

        //找到最新评论

        Comment latestComment=commentList.get(0);
        for(Comment ct:commentList){
            if(ct.getLocalDateTime().isBefore(latestComment.getLocalDateTime()))
                latestComment=ct;
        }
        mv.addObject("latestComment",latestComment);
        //找到最近一次考试成绩
        Score latestScore=scoreList.get(0);
        for(Score sc:scoreList){
            if(sc.getLocalDateTime().isBefore(latestScore.getLocalDateTime()))
                latestScore=sc;
        }


        //分数排序
        Comparator<Score> cp = new Comparator<Score>(){
            public int compare(Score c1,Score c2){
                if(c1.getLocalDateTime().isBefore(c2.getLocalDateTime()))
                    return -1;
                else
                    return 1;
            }
        };
        Collections.sort(scoreList,cp);
        List<Score> latestScoreList=scoreList.subList(0,6);


        //最近六次分数
        mv.addObject("latestScoreList",latestScoreList);
        //学生

        mv.addObject("student",student);
        return mv;
    }*/



}
