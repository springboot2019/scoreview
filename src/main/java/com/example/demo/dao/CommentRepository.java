package com.example.demo.dao;

import com.example.demo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    //@Query(value = "select * from score where score.student_id_stu_id=?1",nativeQuery = true)

    /*测试excel需要注释
    @Query("select ct from Comment ct where ct.score.id=?1")
    Comment findCommentByScore_id(Integer score_id);

    @Query("select ct from Comment ct where ct.score.student.stu_id=?1")
    List<Comment> findCommentByStu_id(Integer stu_id);
    */


}
