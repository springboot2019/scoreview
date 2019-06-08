package com.example.demo.controller;


import com.example.demo.dao.StudentRepository;
import com.example.demo.dao.ScoreRepository;
import com.example.demo.entity.Score;
import com.example.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/manage")
public class TeacherController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    //增加成绩信息
    @GetMapping(value = "/add")
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("add");
        return modelAndView;
    }

    @PostMapping(path = "/add")

    public ModelAndView addNewScore(ModelAndView modelAndView,
                                   @RequestParam("stuId") Integer stuId,
                                   @RequestParam("title") String title,
                                   @RequestParam("chinese") Integer chinese,
                                   @RequestParam("math") Integer math,
                                   @RequestParam("english") Integer english,
                                   @RequestParam("physics") Integer physics,
                                   @RequestParam("chemistry") Integer chemistry,
                                   @RequestParam("biology") Integer biology,
                                   BindingResult bindingResult,
                                   RedirectAttributes attr){
        if(bindingResult.hasErrors()){
            modelAndView.addObject("error",bindingResult.getFieldError().getDefaultMessage());
            modelAndView.setViewName("add");
            return modelAndView;
        }

        Student student = studentRepository.getOne(stuId);
        Score score=new Score();
        score.setTitle(title);

        score.setStudent(student);
        score.setChinese(chinese);
        score.setMath(math);
        score.setEnglish(english);
        score.setPhysics(physics);
        score.setChemistry(chemistry);
        score.setBiology(biology);
        score.setLocalDateTime(LocalDateTime.now());
        scoreRepository.save(score);



        attr.addFlashAttribute("stu_id",student.getStu_id());
        //重定向到某页面
        //modelAndView.setViewName("redirect:");
        return modelAndView;
    }

    @PostMapping(path = "/delete")

    public ModelAndView addNewScore(ModelAndView modelAndView,
                                    @RequestParam("stuId") Integer stuId,
                                    @RequestParam("title") String title,
                                    BindingResult bindingResult,
                                    RedirectAttributes attr){
        if(bindingResult.hasErrors()){
            modelAndView.addObject("error",bindingResult.getFieldError().getDefaultMessage());
            modelAndView.setViewName("delete");
            return modelAndView;
        }

        Student student = studentRepository.getOne(stuId);
        List<Score> scoreList=scoreRepository.findScoreByStu_idAndTitle(stuId,title);
        scoreRepository.deleteAll(scoreList);

        attr.addFlashAttribute("stu_id",student.getStu_id());
        //重定向到某页面
        //modelAndView.setViewName("redirect:");
        return modelAndView;
    }
    @PostMapping(path = "/update")

    public ModelAndView UpdateScore(ModelAndView modelAndView,
                                    @RequestParam("stuId") Integer stuId,
                                    @RequestParam("title") String title,
                                    @RequestParam("chinese") Integer chinese,
                                    @RequestParam("math") Integer math,
                                    @RequestParam("english") Integer english,
                                    @RequestParam("physics") Integer physics,
                                    @RequestParam("chemistry") Integer chemistry,
                                    @RequestParam("biology") Integer biology,
                                    BindingResult bindingResult,
                                    RedirectAttributes attr){
        if(bindingResult.hasErrors()){
            modelAndView.addObject("error",bindingResult.getFieldError().getDefaultMessage());
            modelAndView.setViewName("login");
            return modelAndView;
        }


        scoreRepository.updateScoreByStuIdAndTitle(stuId,title,chinese,math,english,physics,chemistry,biology);

        attr.addFlashAttribute("stu_id",stuId);
        //重定向到某页面
        //modelAndView.setViewName("redirect:");
        return modelAndView;
    }




}


