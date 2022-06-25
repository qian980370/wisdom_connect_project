package com.project.wisdomconnect.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("hobbyrelationship")
public class HobbyRelationship {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer profileid;
    private Integer hobbyid;

}
