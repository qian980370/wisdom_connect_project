package com.project.wisdomconnect.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.wisdomconnect.entity.ResultMessage;

public class MessageUtils {
    //message builder
    public static String getMessage(boolean isSystemMessage, boolean isGetRoomID, String fromName, Object message){
        try {
            ResultMessage result = new ResultMessage();
            result.setIsSystem(isSystemMessage);
            result.setIsGetRoomID(isGetRoomID);
            result.setMessage(message);
            if (fromName!=null){
                //set target name
                result.setFromName(fromName);
            }

            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(result);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }
}

