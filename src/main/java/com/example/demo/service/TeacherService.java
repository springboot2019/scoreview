package com.example.demo.service;

import com.example.demo.entity.Score;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TeacherService {
    boolean register(Teacher teacher);
    //获取所有学生信息
     public List getStudents();
    //根据学号获取学生信息
    public Object getStudentByStu_id(@PathVariable("stu_id")Integer stu_id);
    //根据学号获取学生成绩信息
    public Object getStudentScoreByStu_id(@PathVariable("student_id")Integer stu_id);
    //根据班级获取学生信息
    public Object getStudentByStu_class(@PathVariable Integer stu_class);
    //增加一个新的学生信息到数据库
    public void addStudent(Integer stuId, String password,
                             String sex, Integer stu_class, String stuName);
    public Student updateStudentById(Integer stuId, String password,
                                     String sex, Integer stu_class, String stuName);
    public void deleteById(Integer stu_id);
    public Score addNewScore(Integer stuId,
                             String title,
                             Integer chinese,
                             Integer math,
                             Integer english,
                             Integer physics,
                             Integer chemistry,
                             Integer biology);
    public void deleteScore(Integer stuId, String title);
    public void updateScore(Integer stuId,
                            String title,
                            Integer chinese,
                            Integer math,
                            Integer english,
                            Integer physics,
                            Integer chemistry,
                            Integer biology);
}
