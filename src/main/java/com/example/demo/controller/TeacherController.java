package com.example.demo.controller;


import com.example.demo.dao.StudentRepository;
import com.example.demo.dao.ScoreRepository;
import com.example.demo.entity.Score;
import com.example.demo.entity.Student;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(value = "/")
//不能是/manage，只能这种方式，不然thymeleaf解析错误。玄学问题。
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentRepository studentRepository;//没时间对student进行Service分层了，将就用着
    @GetMapping(value = "/index")
    public ModelAndView teacherIndex(){
        return new ModelAndView("/index");
    }
    //获取所有学生信息
    @PostMapping(value = "/all_students")
    @ResponseBody
    public Map getStudents(){
        List<Student> studentList=teacherService.getStudents();
        Map resultMap=new HashMap();
        resultMap.put("total",studentList.size());
        resultMap.put("rows",studentList);
        return resultMap;
    }
    //根据学号获取学生信息
    @GetMapping(value = "/students/{stu_id}")
    public Object getStudentByStu_id(@PathVariable("stu_id")Integer stu_id){
        /*
        Student student= studentRepository.getOne(stu_id);
        if(student!=null)
            //不要用toString，不然Postman解析不了，因为toString转换后并不是json字符串
            return student;
        else
            return "无该学生信息。";*/
        return teacherService.getStudentByStu_id(stu_id);
    }
    //根据学号获取学生成绩信息
    @GetMapping(value = "/scores/{student_id}")
    public Object getStudentScoreByStu_id(@PathVariable("student_id")Integer stu_id){
        return teacherService.getStudentScoreByStu_id(stu_id);
    }
    //根据班级获取学生信息
    @GetMapping(value = "/teacher/{stu_class}")
    public Object getStudentByStu_class(@PathVariable Integer stu_class){
        return teacherService.getStudentByStu_class(stu_class);
    }
    /**
     * 增加一个新的学生信息到数据库：
     //* @param student 前端传过来的参数
    // * @param bindingResult 可以理解为异常捕获类
     * @return

     */

    @PostMapping(value = "/students/add")
    public String addStudent(@RequestParam Integer stu_id,
                                         @RequestParam String password,
                                         @RequestParam String sex,
                                         @RequestParam Integer stu_class,
                                         @RequestParam String stuName){
       /* BindingResult bindingResult
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return "不符合条件，插入失败，请检查是不是有不正确的请求参数";
        }*/
        Map<String,String> map=new HashMap<>();
        try{
                if(!studentRepository.existsById(stu_id))
                teacherService.addStudent(stu_id,password,sex,stu_class,stuName);
                else
                studentRepository.updateStudentByStu_id(stu_id,password,sex,stu_class,stuName);
                map.put("success","true");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","请核对信息确保登录名唯一");
        }
        return "redirect:/index";
    }

    @PostMapping(value = "/students/update")

    public Object updateStudentById(@RequestParam Integer stu_id,@RequestParam String password,
                                     @RequestParam String sex,
                                     @RequestParam Integer stu_class,@RequestParam String stuName,
                                     BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return "不符合条件，插入失败，请检查是不是有不正确的请求参数";
        }
        Student student=teacherService.updateStudentById(stu_id,password,sex,stu_class,stuName);
        return student;
    }

    /**
     * 删除指定ID的学生信息
     * @param stu_id
     * @return

     */

    @PostMapping(value ="/students/delete")
    public String deleteById(@RequestParam(value = "stu_id") Integer stu_id){
        teacherService.deleteById(stu_id);
        return "redirect:/index";
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
        teacherService.addNewScore(stuId,title,chinese,math,english,physics,chemistry,biology);
        attr.addFlashAttribute("stu_id",stuId);
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
        teacherService.deleteScore(stuId, title);
       // attr.addFlashAttribute("stu_id",student.getStu_id());
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
        teacherService.updateScore(stuId,title,chinese,math,english,physics,chemistry,biology);
        attr.addFlashAttribute("stu_id",stuId);
        //重定向到某页面
        //modelAndView.setViewName("redirect:");
        return modelAndView;
    }
}


