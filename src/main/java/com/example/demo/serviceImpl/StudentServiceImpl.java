package com.example.demo.serviceImpl;

import com.example.demo.dao.StudentRepository;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Override
    public boolean Register(Student student) {
        int stu_id=student.getStu_id();
        if(studentRepository.existsById(stu_id))
            return false;
        else if(student.getStu_id()==null||student.getPassword()==null||
                student.getStuName()==null||student.getSex()==null||student.getStu_class()==null){
            return false;
        }
        studentRepository.save(student);
        return true;
    }
}
