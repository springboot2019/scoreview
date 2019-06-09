package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Score implements Serializable{
    @Id
    @GeneratedValue
    @GenericGenerator(name = "system-unid", strategy = "uuid")
    private Integer id;
    private String title;

   /*@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false,fetch = FetchType.EAGER,targetEntity = Student.class)
   @JoinColumn(referencedColumnName = "stu_id",foreignKey = @ForeignKey(name="FK_stu_score"))*/
   @ManyToOne(fetch=FetchType.EAGER,targetEntity=Student.class)
   @JoinColumn(name="student",referencedColumnName = "stu_id",
   foreignKey = @ForeignKey(name ="FK_Sco_Student"))
   //student是列名，stu_id是外键所在表的属性名(Score内属性为Student但实际上数据库表内列存放stu_id)
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
        return this.biology+this.chemistry+this.chinese+this.english+this.math+this.physics;
    }

}
