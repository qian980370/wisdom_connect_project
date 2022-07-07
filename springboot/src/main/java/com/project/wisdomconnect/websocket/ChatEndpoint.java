package com.project.wisdomconnect.websocket;

import com.project.wisdomconnect.mapper.ProfileMapper;
import com.project.wisdomconnect.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat/{profileID}")
@Component
@Slf4j
public class ChatEndpoint {

    @Resource
    ProfileMapper profileMapper;

    //store the client end sessions
    private static Map<String, Session> onlineSessions  = new ConcurrentHashMap<>();



    @OnOpen
    public void onOpen(Session session, @PathParam("profileID") String profileID){
        log.info("build session {}", profileID);
        onlineSessions.put(profileID, session);

    }



    @OnMessage
    public void onMessage(String message,Session session) throws IOException {
        log.info("message is {}",message);
        session.getBasicRemote().sendText("online");

    }

    @OnClose
    public void onClose(@PathParam("profileID") String profileID) {
        log.info("close session {}", profileID);
//        onlineSessions.remove(profileID);
    }


}
