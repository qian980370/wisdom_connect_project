package com.project.wisdomconnect.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("file")
public class Files {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String type;
    private String md5;
    private Long size;
    private String url;
    private String isDelete;
    private String enable;

}
