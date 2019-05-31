package com.example.demo.dao;

import com.example.demo.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ScoreRepository extends JpaRepository<Score,Integer> {
    //@Query(value = "select * from score where score.student_id_stu_id=?1",nativeQuery = true)
    @Query("select sc from Score sc where sc.student_id.stu_id=?1")
    Score findScoreByStu_id(@Param("id")Integer stu_id);
}
