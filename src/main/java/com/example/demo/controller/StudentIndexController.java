package com.example.demo.controller;

import com.example.demo.dao.CommentRepository;
import com.example.demo.dao.ScoreRepository;
import com.example.demo.dao.StudentRepository;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Score;
import com.example.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@RestController
@RequestMapping("/")
public class StudentIndexController {
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CommentRepository commentRepository;


    @RequestMapping (value = "/recent_scores")
    public ModelAndView showIndex(@ModelAttribute("stu_id")Integer stuId)

    {
                //(Integer) (modelAndView.getModel().get("stu_id"));
        ModelAndView mv = new ModelAndView();
        List<Score> scoreList = scoreRepository.findScoreByStu_id(stuId);
        List<Comment> commentList = commentRepository.findCommentByStu_id(stuId);
        Student student = studentRepository.getOne(stuId);
        //错误处理 (万恶之首
       /*if(scoreList.size()==0||commentList.size()==0||student==null){
            mv.addObject("errors","某项记录返回为空");
            System.out.println("这里！");
            return mv;
        }*/

        //找到最新评论
        Comment latestComment;
        if(commentList.size()>0){
        latestComment=commentList.get(0);
        for(Comment ct:commentList){
            if(ct.getLocalDateTime().isBefore(latestComment.getLocalDateTime()))
                latestComment=ct;
        }
        }
        else {
            latestComment=null;
        }
        mv.addObject("latestComment",latestComment);
        //找到最近一次考试成绩
        Score latestScore=null;
        if(scoreList.size()>0) {
            latestScore = scoreList.get(0);
            for (Score sc : scoreList) {
                if (sc.getLocalDateTime().isBefore(latestScore.getLocalDateTime()))
                    latestScore = sc;
            }
        }
        else {
            mv.addObject("latestScoreList",null);
            mv.addObject("totalList",null);
            mv.addObject("student",student);
            mv.setViewName("recent_scores");
            return mv;
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
        Comparator<Score> cp1 = new Comparator<Score>(){
            public int compare(Score c1,Score c2){
                if(c1.getLocalDateTime().isBefore(c2.getLocalDateTime()))
                    return 1;
                else
                    return -1;
            }
        };
        Collections.sort(scoreList,cp);
        List<Score> latestScoreList;
        if(scoreList.size()>=6){
             latestScoreList=scoreList.subList(0,6);
        }
        else {
            latestScoreList=scoreList.subList(0,scoreList.size());
        }

       // Collections.sort(latestScoreList,cp1);
        /*
        //最近一次成绩各科分数

        latestScore=scoreList.get(0);
        Map<String,Integer> latestScoreMap = new HashMap<String, Integer>();
        latestScoreMap.put("biology",latestScore.getBiology());
        latestScoreMap.put("chemistry",latestScore.getChemistry());
        latestScoreMap.put("chinese",latestScore.getChinese());
        latestScoreMap.put("english",latestScore.getEnglish());
        latestScoreMap.put("math",latestScore.getMath());
        latestScoreMap.put("physics",latestScore.getPhysics());
        mv.addObject("latestScoreMap",latestScoreMap);
        */

        //最近六次分数
        mv.addObject("latestScoreList",latestScoreList);
        //学生
        List<Integer> totalList=new ArrayList<>();
        for(Score each:latestScoreList){
            totalList.add(each.getTotal());
        }
        Collections.reverse(totalList);
        mv.addObject("totalList",totalList);
        mv.addObject("student",student);
        mv.setViewName("recent_scores");
      //  System.out.println(mv.getModel().get("latestScoreList"));
        return mv;
    }
}
