package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer stu_id;//学号，同时也是登录账号
    private String password;
    private String stu_class;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stu_id=" + stu_id +
                ", password='" + password + '\'' +
                ", stu_class='" + stu_class + '\'' +
                '}';
    }

}
