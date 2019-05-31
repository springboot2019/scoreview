package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

@Data
@Entity
public class Student implements Serializable{
    @Id
    @Column(unique = true)
    @NotEmpty(message = "学号不能为空")
    private Integer stu_id;//学号，同时也是登录账号
    @Size(min=4,max=10,message = "密码必须6-10位")
    private String password;
    private String stu_class;
   /* @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private List<Score> score;*/
   /* @OneToMany(mappedBy = "stu_id",cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    private Vector<Score> scores;*/
    public Student(){super();}
}
