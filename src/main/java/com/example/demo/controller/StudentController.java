package com.example.demo.controller;


import com.example.demo.dao.DBUtils;
import com.example.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private DBUtils dbUtils;

    //获取所有学生信息
    @GetMapping(value="/students")
    public List getStudents(){
        return null;
    }
    //根据学号获取学生信息
    @GetMapping(value = "/students/{stu_id}")
    public Student getStudentByStu_id(@PathVariable("stu_id")int stu_id){
        return null;
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
        dbUtils.save(student);
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
}
