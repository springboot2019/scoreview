package com.example.demo.controller;

import com.example.demo.dao.CommentRepository;
import com.example.demo.dao.ScoreRepository;
import com.example.demo.dao.StudentRepository;
import com.example.demo.entity.Score;
import com.example.demo.entity.Student;
import com.example.demo.myutil.ExcelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/excel")
public class ExcelController {
    ExcelBuilder excelBuilder = ExcelBuilder.getInstance();
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private StudentRepository studentRepository;
    @RequestMapping("/load")
    public void loadExcelToDataBase(
            @RequestParam(value="fileName",required = false) String fileName
            ){

        ArrayList<Map<String,Object>> mapList = excelBuilder.loadScoreInfo(fileName);
        for(int i=0;i<mapList.size();i++){
            Student student = studentRepository.findStudentByStu_id((Integer) mapList.get(i).get("stuId"));
            System.out.println(mapList.get(i).get("stuId"));
            System.out.println((String)mapList.get(i).get("stuName"));
            System.out.println(student.getStuName());
            //检查学生是否已注册以及姓名学号是否匹配
            if(student==null||!((String)mapList.get(i).get("stuName")).equals(student.getStuName())){
                continue;
            }
            Score score;
            score=(Score)mapList.get(i).get("score");
            score.setStudent(student);

            scoreRepository.save(score);
            //System.out.println("sava one");

        }

    }

    @RequestMapping("/create")
    public void createExcel(String str){
        try{
            ExcelBuilder.createExcel();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


}
