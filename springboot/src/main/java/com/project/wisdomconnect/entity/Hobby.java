package com.project.wisdomconnect.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("hobby")
public class Hobby {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer level;
    private String tophobbyname;
    private String icon;

}
