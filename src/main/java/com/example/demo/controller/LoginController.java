package com.example.demo.controller;

import com.example.demo.dao.StudentRepository;
import com.example.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    private StudentRepository studentRepository;
    @GetMapping(value = "/login")
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @PostMapping(value="/login")
    public ModelAndView login(ModelAndView modelAndView, @RequestParam Integer stu_id, @RequestParam String password, BindingResult bindingResult, RedirectAttributes attr){
        if(bindingResult.hasErrors()){
            modelAndView.addObject("error",bindingResult.getFieldError().getDefaultMessage());
            modelAndView.setViewName("login");
            return modelAndView;
        }
       // Integer stu_id = student.getStu_id();
       // String password = student.getPassword();
        Student currentUser=studentRepository.getOne(stu_id);
        if(currentUser==null){
            modelAndView.addObject("error","无此用户！");
            modelAndView.setViewName("login");
            return modelAndView;
        }
        else if(!currentUser.getPassword().equals(password)){
            modelAndView.addObject("error","密码错误！");
            modelAndView.setViewName("login");
            return modelAndView;
        }
        attr.addFlashAttribute("stu_id",currentUser.getStu_id());
        //modelAndView.addObject("stu_id",currentUser.getStu_id());
        modelAndView.setViewName("redirect:/recent_scores");//
        return modelAndView;
    }
/*
    @RequestMapping(value = "/succeced")
    public String loginSucceced(ModelAndView modelAndView){
        return "succeced";
    }*/
}
