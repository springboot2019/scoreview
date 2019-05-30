package com.example.demo.dao;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBUtils extends JpaRepository <Student,Integer>{
   // public Student findByStu_id(int id);
}
