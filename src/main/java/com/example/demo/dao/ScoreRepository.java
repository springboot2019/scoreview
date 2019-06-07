package com.example.demo.dao;

import com.example.demo.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreRepository extends JpaRepository<Score,Integer> {
    //@Query(value = "select * from score where score.student_id_stu_id=?1",nativeQuery = true)
    @Query("from Score sc where sc.student.stu_id=?1")
    List<Score> findScoreByStu_id(Integer stu_id);

    @Query("from Score sc where sc.student.stu_id=?1 and sc.title like concat('%',?2,'%')")
    List<Score> findScoreByStu_idAndTitle(Integer stu_id,String title);

    @Query(value = "update Score set  chinese = :chinese,math = :math,english=:english,physics=:physics,chemistry=:chemistry,biology=:biology where stuId = :stuId and title =:title")
    void updateScoreByStuIdAndTitle(Integer stuId,
                                    String title,
                                    Integer chinese,
                                    Integer math,
                                    Integer english,
                                    Integer physics,
                                    Integer chemistry,
                                    Integer biology);

 /*   void updateScoreByStuIdAndTitle(@RequestParam("stuId") Integer stuId,
    @RequestParam("title") String title,
    @RequestParam("chinese") Integer chinese,
    @RequestParam("math") Integer math,
    @RequestParam("english") Integer english,
    @RequestParam("physics") Integer physics,
    @RequestParam("chemistry") Integer chemistry,
    @RequestParam("biology") Integer biology);*/
//@Request之类的注解是控制器用的
}
