package com.project.wisdomconnect.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("friendrelationship")
public class FriendRelationship {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer profileid1;
    private Integer profileid2;

    private Integer state;
}
