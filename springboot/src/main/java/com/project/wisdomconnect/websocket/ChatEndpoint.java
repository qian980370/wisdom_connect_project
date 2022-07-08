package com.project.wisdomconnect.websocket;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.project.wisdomconnect.common.Constants;
import com.project.wisdomconnect.common.Result;
import com.project.wisdomconnect.entity.FriendRelationship;
import com.project.wisdomconnect.entity.Profile;
import com.project.wisdomconnect.entity.User;
import com.project.wisdomconnect.mapper.FriendRelationshipMapper;
import com.project.wisdomconnect.mapper.ProfileMapper;
import com.project.wisdomconnect.utils.MessageUtils;
import com.project.wisdomconnect.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat/{profileID}")
@Component
@Slf4j
public class ChatEndpoint {

    @Autowired
    private ProfileMapper profileMapper;
    @Autowired
    FriendRelationshipMapper friendRelationshipMapper;

    //store the client end sessions
    private static Map<Integer, Session> onlineSessions  = new ConcurrentHashMap<>();

    private static ChatEndpoint chatEndpoint;

    @PostConstruct
    private void init(){
        chatEndpoint = this;
        chatEndpoint.profileMapper = this.profileMapper;
        chatEndpoint.friendRelationshipMapper = this.friendRelationshipMapper;
    }


    @OnOpen
    public void onOpen(Session session, @PathParam("profileID") String profileID) throws IOException {
        log.info("build session {}", profileID);
        onlineSessions.put(Integer.valueOf(profileID), session);
        broadcastOnline(Integer.valueOf(profileID));


    }

    private void broadcastOnline(Integer profileID) throws IOException {
        LambdaQueryWrapper<Profile> profileWrapper = Wrappers.<Profile>lambdaQuery().orderByAsc(Profile::getId);
        profileWrapper.like(Profile::getId, profileID);
        Profile profile = chatEndpoint.profileMapper.selectOne(profileWrapper);
        if(profile != null){
            profile.setOnline(1);
            chatEndpoint.profileMapper.updateById(profile);

            LambdaQueryWrapper<FriendRelationship> wrapper = Wrappers.<FriendRelationship>lambdaQuery().orderByAsc(FriendRelationship::getId);

            wrapper.like(FriendRelationship::getProfileid1, profileID).ne(FriendRelationship::getState, 0).or().like(FriendRelationship::getProfileid2, profileID).ne(FriendRelationship::getState, 0);
            List<FriendRelationship> result = chatEndpoint.friendRelationshipMapper.selectList(wrapper);
            Integer target = null;

            String broadcastMessage = MessageUtils.getMessage(true, profile.getUsername(), "friend online notification");
            for (FriendRelationship f : result){
                if (!Objects.equals(f.getProfileid1(), profileID)){
                    target = f.getProfileid1();
                }else {
                    target = f.getProfileid2();
                }
                if(onlineSessions.get(target) != null){
                    onlineSessions.get(target).getBasicRemote().sendText(broadcastMessage);
                }
            }
        }
    }

    private void broadcastOffline(Integer profileID) throws IOException {

        LambdaQueryWrapper<Profile> profileWrapper = Wrappers.<Profile>lambdaQuery().orderByAsc(Profile::getId);
        profileWrapper.like(Profile::getId, profileID);
        Profile profile = chatEndpoint.profileMapper.selectOne(profileWrapper);
        if(profile != null){
            profile.setOnline(0);
            chatEndpoint.profileMapper.updateById(profile);

            LambdaQueryWrapper<FriendRelationship> wrapper = Wrappers.<FriendRelationship>lambdaQuery().orderByAsc(FriendRelationship::getId);

            wrapper.like(FriendRelationship::getProfileid1, profileID).ne(FriendRelationship::getState, 0).or().like(FriendRelationship::getProfileid2, profileID).ne(FriendRelationship::getState, 0);
            List<FriendRelationship> result = chatEndpoint.friendRelationshipMapper.selectList(wrapper);
            Integer target = null;

            String broadcastMessage = MessageUtils.getMessage(true, profile.getUsername(), "friend Offline notification");
            for (FriendRelationship f : result){
                if (!Objects.equals(f.getProfileid1(), profileID)){
                    target = f.getProfileid1();
                }else {
                    target = f.getProfileid2();
                }
                if(onlineSessions.get(target) != null){
                    onlineSessions.get(target).getBasicRemote().sendText(broadcastMessage);
                }
            }
        }
    }

    @OnMessage
    public void onMessage(String message,Session session) throws IOException {
        log.info("message is {}",message);
        session.getBasicRemote().sendText("online");

    }

    @OnClose
    public void onClose(@PathParam("profileID") String profileID) throws IOException {
        log.info("close session {}", profileID);

        broadcastOffline(Integer.valueOf(profileID));
        onlineSessions.remove(Integer.valueOf(profileID));
    }


}
