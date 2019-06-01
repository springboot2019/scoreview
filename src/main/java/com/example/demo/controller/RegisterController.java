package com.example.demo.controller;

import com.example.demo.dao.StudentRepository;
import com.example.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class RegisterController {
    @Autowired
    private StudentRepository studentRepository;
    @GetMapping(value = "/register")
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("register");
        return modelAndView;
    }
    @PostMapping(value = "/register")
    public ModelAndView register(ModelAndView modelAndView, @Valid @RequestBody Student student, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            modelAndView.addObject("error",bindingResult.getFieldError().getDefaultMessage());
            modelAndView.setViewName("register");
            return modelAndView;
        }
        studentRepository.save(student);
        modelAndView.setViewName("/succeced");
        return modelAndView;
    }
}
