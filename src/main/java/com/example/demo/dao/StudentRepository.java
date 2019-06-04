package com.example.demo.dao;

import com.example.demo.entity.Score;
import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository <Student,Integer>{
    @Query("from Student st where st.stu_id=?1")
    Student findStudentByStu_id(Integer stu_id);
}
