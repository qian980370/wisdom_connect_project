package com.project.wisdomconnect.entity;

import lombok.Data;

@Data
public class ResultMessage {
    private Boolean isSystem;
    private Boolean isGetRoomID;
    private String fromName;
    private Object message;
}
