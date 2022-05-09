package com.project.wisdomconnect.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("fastcode")
public class FastCode {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer manager;
    private String role;
    private String code;
    private String md5;
    private String name;
    private String email;
    private String address;
    private String abn;
    private String state;
}
