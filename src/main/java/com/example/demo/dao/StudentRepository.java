package com.example.demo.dao;

import com.example.demo.entity.Score;
import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository <Student,Integer>{
    @Query("from Student st where st.stu_id=?1")
    Student findStudentByStu_id(Integer stu_id);
    @Query("from Student st where st.stu_class=?1")
    List<Student> findStudentsByStu_class(Integer stu_class);
    @Modifying
    @Transactional
    @Query("update Student st set st.password=:password,st.sex=:sex,st.stu_class=:stu_class,st.stuName=:stuName where st.stu_id=:stu_id")
    void updateStudentByStu_id(Integer stu_id,String password,String sex,
                               Integer stu_class,String stuName);

}
