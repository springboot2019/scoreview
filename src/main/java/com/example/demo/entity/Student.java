package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Student implements Serializable {
    private static final long serialVersionUID = -3417930882448168180L;
    @Id
    private Integer stu_id;//学号，同时也是登录账号
    private String password;
    private String stu_class;
    @OneToMany(mappedBy = "owner",cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Score> scores;

    @Override
    public String toString() {
        return "Student{" +
                "stu_id=" + stu_id +
                ", password='" + password + '\'' +
                ", stu_class='" + stu_class + '\'' +
                ", scores=" + scores.indexOf(0) +
                '}';
    }
}
