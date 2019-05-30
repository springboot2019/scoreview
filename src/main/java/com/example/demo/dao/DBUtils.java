package com.example.demo.dao;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DBUtils extends JpaRepository <Student,Integer>{
   // @Query("select new  from Student where Student.stu_id=id")
   // public Student findByStu_id(@Param("id")Integer stu_id);
}
