package com.project.wisdomconnect.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("chat")
public class Chat {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer holderone;
    private Integer holdertwo;
    private String message;
    private Integer calling;

}
