package com.example.demo.serviceImpl;

import com.example.demo.dao.ScoreRepository;
import com.example.demo.dao.StudentRepository;
import com.example.demo.entity.Score;
import com.example.demo.entity.Student;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ScoreRepository scoreRepository;
    @Override
    public List getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Object getStudentByStu_id(Integer stu_id) {
        Student student= studentRepository.getOne(stu_id);
        return student;
    }

    @Override
    public Object getStudentScoreByStu_id(Integer stu_id) {
        List<Score> scores=scoreRepository.findScoreByStu_id(stu_id);
        return scores;
    }

    @Override
    public Object getStudentByStu_class(Integer stu_class) {
        List<Student> students=studentRepository.findStudentsByStu_class(stu_class);
        return students;
    }

    @Override
    public void addStudent(Integer stuId, String password, String sex, Integer stu_class, String stuName) {
        Student student=new Student();
        student.setStu_id(stuId);
        student.setStuName(stuName);
        student.setSex(sex);
        student.setPassword(password);
        student.setStu_class(stu_class);
        studentRepository.save(student);
    }

    @Override
    public Student updateStudentById(Integer stuId, String password,
                                     String sex, Integer stu_class, String stuName) {
        Student student=studentRepository.getOne(stuId);
        student.setStu_class(stu_class);
        student.setPassword(password);
        student.setStuName(stuName);
        student.setSex(sex);
        return student;
    }

    @Override
    public void deleteById(Integer stu_id) {
        studentRepository.deleteById(stu_id);
    }
    @Override
    public Score addNewScore(Integer stuId,
                                    String title,
                                    Integer chinese,
                                    Integer math,
                                    Integer english,
                                    Integer physics,
                                    Integer chemistry,
                                    Integer biology){
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
        return score;
    }

    @Override
    public void deleteScore(Integer stuId, String title) {
        Student student = studentRepository.getOne(stuId);
        Score score=scoreRepository.findScoreByStu_idAndTitle(stuId,title);
        scoreRepository.delete(score);
    }

    @Override
    public void updateScore(Integer stuId, String title, Integer chinese, Integer math, Integer english, Integer physics, Integer chemistry, Integer biology) {
        scoreRepository.updateScoreByStuIdAndTitle(stuId,title,chinese,math,
                english,physics,chemistry,biology);
    }
}
