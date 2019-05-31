package com.example.demo.controller;


import com.example.demo.dao.DBUtils;
import com.example.demo.dao.ScoreRepository;
import com.example.demo.entity.Score;
import com.example.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private DBUtils dbUtils;
    @Autowired
    private ScoreRepository scoreRepository;
    //获取所有学生信息
    @GetMapping(value="/students")
    public List getStudents(){
        return null;
    }
    //根据学号获取学生信息
    @GetMapping(value = "/students/{stu_id}")
    public Object getStudentByStu_id(@PathVariable("stu_id")Integer stu_id){
        Student student=dbUtils.getOne(stu_id);
        if(student!=null)
            return student.toString();
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
