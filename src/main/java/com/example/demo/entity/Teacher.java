package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Teacher implements Serializable {
    @Id
    @Column(unique = true)
    @NotNull(message = "学号不能为空")
    //老师工号，同时也是登录账号
    private Integer tea_id;
    @Size(min=4,max=10,message = "密码必须6-10位")
    private String password;

    public Teacher() {
        super();
    }
}
