package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

@Data
@Entity
public class Student{
    @Id
    @Column(unique = true)
    private Integer stu_id;//学号，同时也是登录账号
    private String password;
    private String stu_class;
   /* @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private List<Score> score;*/
   /* @OneToMany(mappedBy = "stu_id",cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    private Vector<Score> scores;*/
    public Student(){super();}
}
