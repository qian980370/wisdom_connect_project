package com.project.wisdomconnect.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("profile")
public class Profile {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer owner;
    private String username;


    private Integer privacy;



    private Integer age;
    private String gender;

    private String icon;
    private Integer online;
    private Integer oncall;
    private String lastLogin;
}
