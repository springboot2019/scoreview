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

@RestController
@RequestMapping(value = "/manage")
public class TeacherController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ScoreRepository scoreRepository;
    //获取所有学生信息
    @GetMapping(value="/all_students")
    public List getStudents(){
        return null;
    }
    //根据学号获取学生信息
    @GetMapping(value = "/students/{stu_id}")
    public Object getStudentByStu_id(@PathVariable("stu_id")Integer stu_id){
        Student student= studentRepository.getOne(stu_id);
        if(student!=null)
            //不要用toString，不然Postman解析不了，因为toString转换后并不是json字符串
            return student;
        else
            return "无该学生信息。";
    }
    //根据学号获取学生成绩信息
    @GetMapping(value = "/scores/{student_id}")
    public Object getStudentScoreByStu_id(@PathVariable("student_id")Integer stu_id){
        List<Score> scores=scoreRepository.findScoreByStu_id(stu_id);
        if(scores!=null)
            return scores;
        else
            return "暂无成绩";
    }
    /**
     * 增加一个新的学生信息到数据库：
     * @param student 前端传过来的参数
     * @param bindingResult 可以理解为异常捕获类
     * @return

     */

    @PostMapping(value = "/students/add")

    public Object addStudent(@Valid Student student, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return "不符合条件，插入失败，请检查是不是有不正确的请求参数";
        }
        studentRepository.save(student);
        return student;
    }

    @PostMapping(value = "/students/update")

    public Student updateStudentById(){

        return null;

    }

    /**
     * 删除指定ID的学生信息
     * @param stu_id
     * @return

     */

    @PostMapping(value ="/students/delete")

    public String deleteById(@RequestParam(value = "stu_id") int stu_id){

        return null;

    }
    //增加成绩信息
    @GetMapping(value = "/scores/add")
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("add");
        return modelAndView;
    }
/*展示页面用get，提交信息用post(用到requestparam的基本都是post))
    @GetMapping(value = "/scores/add")
    @ResponseBody*/
    @PostMapping(value = "/scores/add")
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

    @GetMapping(path = "/delete")
    @ResponseBody
    public ModelAndView deleteScore(ModelAndView modelAndView,
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
    @GetMapping(path = "/update")
    @ResponseBody

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


