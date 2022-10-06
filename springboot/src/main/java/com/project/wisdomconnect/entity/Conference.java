package com.project.wisdomconnect.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("conference")
public class Conference {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String conferenceid;
    private Integer pin;
    private Integer participantlimit;
    private Integer participantcurr;
    private Integer available;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Integer tenant;
}
