package com.example.demo.controller;

import com.example.demo.dao.StudentRepository;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class RegisterController {
    @Autowired
    StudentService studentService;
    @GetMapping(value = "/register")
    public ModelAndView register(ModelAndView modelAndView){
        modelAndView.setViewName("register");
        return modelAndView;
    }
    //试图用ajax从前端直接传对象，一直报415错误，改了contentType也不行，遂放弃
    //但后台通过postman测试是没问题的
/*
    @ResponseBody
   // @PostMapping(value = "/register",consumes = "application/json")
    @RequestMapping(value ={ "/register"}, method = { RequestMethod.POST})
    public Object register(@Valid @RequestBody Student student, BindingResult bindingResult){
       if(bindingResult.hasErrors()){
            modelAndView.addObject("error",bindingResult.getFieldError().getDefaultMessage());
            modelAndView.setViewName("register");
            return modelAndView;
        }
        studentService.Register(student);
        return "{\"msg\":\"success\"}";
    }*/
    @PostMapping(value = "/register")
    public ModelAndView register(ModelAndView modelAndView, @RequestParam Integer stu_id, @RequestParam String password,
                                 @RequestParam String sex,@RequestParam Integer stu_class,@RequestParam String stuName, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            modelAndView.addObject("error",bindingResult.getFieldError().getDefaultMessage());
            modelAndView.setViewName("login");
            return modelAndView;
        }
        Student student=new Student();
        student.setPassword(password);
        student.setSex(sex);
        student.setStu_class(stu_class);
        student.setStuName(stuName);
        student.setStu_id(stu_id);
        studentService.Register(student);
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
