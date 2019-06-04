package com.example.demo.entity;

import lombok.Data;
//import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class Score implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    //private Integer stu_id;
   @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false,fetch = FetchType.EAGER,targetEntity = Student.class)
   @JoinColumn(referencedColumnName = "stu_id",foreignKey = @ForeignKey(name="FK_stu_score"))
    private Student student;
    private Integer chinese;
    private Integer math;
    private Integer english;
    private Integer physics;
    private Integer chemistry;
    private Integer biology;
    public Score(){super();}
    private LocalDateTime localDateTime;
    public Integer getTotal(){
        return this.biology+this.chemistry+this.chinese+this.english+this.math+this.physics;   }
}
