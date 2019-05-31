package com.example.demo.dao;

import com.example.demo.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreRepository extends JpaRepository<Score,Integer> {
    //@Query(value = "select * from score where score.student_id_stu_id=?1",nativeQuery = true)
    @Query("from Score sc where sc.student_id.stu_id=?1")
    List<Score> findScoreByStu_id(Integer stu_id);
}
