package com.project.wisdomconnect.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@TableName("user")
@Data
@Builder
public class User {
    //@TableId(type = IdType.AUTO);
    private String registerTime;
    private Integer id;
//    private Integer admin;
    private String username;
    private String password;
    private String email;
//    private Integer age;
//    private String gender;
//    private String address;




    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String time){
        this.registerTime = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Integer getAdmin() {
//        return admin;
//    }

//    public void setAdmin(Integer admin) {
//        this.admin = admin;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
}
