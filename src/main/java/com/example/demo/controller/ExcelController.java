package com.example.demo.controller;

import com.example.demo.dao.CommentRepository;
import com.example.demo.dao.ScoreRepository;
import com.example.demo.dao.StudentRepository;
import com.example.demo.entity.Score;
import com.example.demo.entity.Student;
import com.example.demo.myutil.ExcelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class ExcelController {
    ExcelBuilder excelBuilder = ExcelBuilder.getInstance();
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private StudentRepository studentRepository;
    @PostMapping("/excel/load")
    @ResponseBody
    public ModelAndView loadExcelToDataBase(
            @RequestParam(value="fileName",required = false) String fileName
            ){

        ArrayList<Map<String,Object>> mapList = excelBuilder.loadScoreInfo(fileName);
        for(int i=0;i<mapList.size();i++){
            Integer stu_id=(Integer) mapList.get(i).get("stuId");
            Student student = studentRepository.findStudentByStu_id(stu_id);
            /*System.out.println(mapList.get(i).get("stuId"));
            System.out.println((String)mapList.get(i).get("stuName"));
            System.out.println(student.getStuName());*/
            //检查学生是否已注册以及姓名学号是否匹配
            if(student==null){
                student=new Student();
                student.setStu_id(stu_id);
                student.setStuName((String)mapList.get(i).get("stuName"));
                student.setSex("");
                student.setPassword("000000");
                student.setStu_class(0);
                studentRepository.save(student);
            }
            else if(student!=null&&!((String)mapList.get(i).get("stuName")).equals(student.getStuName())){
                studentRepository.updateStudentByStu_id(stu_id,student.getPassword(),student.getSex(),student.getStu_class(),(String)mapList.get(i).get("stuName"));
            }
            Score score;
            score=(Score)mapList.get(i).get("score");
            student=studentRepository.findStudentByStu_id(stu_id);
            score.setStudent(student);
            score.setLocalDateTime(LocalDateTime.now());
            scoreRepository.save(score);
            //System.out.println("sava one");
        }
        return new ModelAndView("redirect:/index");
    }

    @GetMapping("/excel/create")
    @ResponseBody
    public ModelAndView createExcel(){
        //for test
        List<Score> scoreList=scoreRepository.findAll();//findScoreByStu_id(123);
        try{
            ExcelBuilder.createExcel(scoreList);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/index");
    }
}
