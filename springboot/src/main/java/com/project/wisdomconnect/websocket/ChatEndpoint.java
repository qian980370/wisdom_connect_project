package com.project.wisdomconnect.websocket;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.project.wisdomconnect.common.Constants;
import com.project.wisdomconnect.common.Result;
import com.project.wisdomconnect.entity.Chat;
import com.project.wisdomconnect.entity.FriendRelationship;
import com.project.wisdomconnect.entity.Profile;
import com.project.wisdomconnect.entity.User;
import com.project.wisdomconnect.mapper.ChatMapper;
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

@ServerEndpoint("/chat/{token}/{profileID}")
@Component
@Slf4j
public class ChatEndpoint {

    @Autowired
    private ProfileMapper profileMapper;
    @Autowired
    FriendRelationshipMapper friendRelationshipMapper;
    @Autowired
    ChatMapper chatMapper;

    //store the client end sessions
    private static Map<Integer, Session> onlineSessions  = new ConcurrentHashMap<>();

    private static ChatEndpoint chatEndpoint;

    @PostConstruct
    private void init(){
        chatEndpoint = this;
        chatEndpoint.profileMapper = this.profileMapper;
        chatEndpoint.friendRelationshipMapper = this.friendRelationshipMapper;
        chatEndpoint.chatMapper = this.chatMapper;
    }


    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token, @PathParam("profileID") String profileID) throws IOException {
        log.info("build session {}", profileID);
        log.info("build token {}", token);
        User user = TokenUtils.decodeToken(token);
        if (user != null){
            LambdaQueryWrapper<Profile> profileWrapper = Wrappers.<Profile>lambdaQuery().orderByAsc(Profile::getId);
            profileWrapper.like(Profile::getOwner, user.getId());
            profileWrapper.like(Profile::getId, profileID);
            Profile profile = chatEndpoint.profileMapper.selectOne(profileWrapper);
            if(profile != null){
                onlineSessions.put(Integer.valueOf(profileID), session);
                broadcastOnline(Integer.valueOf(profileID));
            }
        }



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

            String broadcastMessage = MessageUtils.getMessage(true, false, profile.getUsername(), "friend online notification");
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
            profile.setOncall(0);
            chatEndpoint.profileMapper.updateById(profile);

            LambdaQueryWrapper<FriendRelationship> wrapper = Wrappers.<FriendRelationship>lambdaQuery().orderByAsc(FriendRelationship::getId);

            wrapper.like(FriendRelationship::getProfileid1, profileID).ne(FriendRelationship::getState, 0).or().like(FriendRelationship::getProfileid2, profileID).ne(FriendRelationship::getState, 0);
            List<FriendRelationship> result = chatEndpoint.friendRelationshipMapper.selectList(wrapper);
            Integer target = null;

            String broadcastMessage = MessageUtils.getMessage(true, false, profile.getUsername(), "friend Offline notification");
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


    private void broadcastOnCall(Integer profileID) throws IOException {
        LambdaQueryWrapper<Profile> profileWrapper = Wrappers.<Profile>lambdaQuery().orderByAsc(Profile::getId);
        profileWrapper.like(Profile::getId, profileID);
        Profile profile = chatEndpoint.profileMapper.selectOne(profileWrapper);
        if(profile != null){
            profile.setOncall(1);
            chatEndpoint.profileMapper.updateById(profile);

            LambdaQueryWrapper<FriendRelationship> wrapper = Wrappers.<FriendRelationship>lambdaQuery().orderByAsc(FriendRelationship::getId);

            wrapper.like(FriendRelationship::getProfileid1, profileID).ne(FriendRelationship::getState, 0).or().like(FriendRelationship::getProfileid2, profileID).ne(FriendRelationship::getState, 0);
            List<FriendRelationship> result = chatEndpoint.friendRelationshipMapper.selectList(wrapper);
            Integer target = null;

            String broadcastMessage = MessageUtils.getMessage(true, false, profile.getUsername(), "friend oncall");
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

    private void broadcastOffCall(Integer profileID) throws IOException {

        LambdaQueryWrapper<Profile> profileWrapper = Wrappers.<Profile>lambdaQuery().orderByAsc(Profile::getId);
        profileWrapper.like(Profile::getId, profileID);
        Profile profile = chatEndpoint.profileMapper.selectOne(profileWrapper);
        if(profile != null){

            profile.setOncall(0);
            chatEndpoint.profileMapper.updateById(profile);

            LambdaQueryWrapper<FriendRelationship> wrapper = Wrappers.<FriendRelationship>lambdaQuery().orderByAsc(FriendRelationship::getId);

            wrapper.like(FriendRelationship::getProfileid1, profileID).ne(FriendRelationship::getState, 0).or().like(FriendRelationship::getProfileid2, profileID).ne(FriendRelationship::getState, 0);
            List<FriendRelationship> result = chatEndpoint.friendRelationshipMapper.selectList(wrapper);
            Integer target = null;

            String broadcastMessage = MessageUtils.getMessage(true, false, profile.getUsername(), "friend OffCall");
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

    @OnMessage   //0 is oncall, 1 is chatting, 2 is refuse, 3 is noresponse, 4 is closed
    public void onMessage(String message,Session session) throws IOException {
        log.info("message is {}",message);
        //transfer string to map
        Map<String, String> dict = transferMessage(message);

        out: if (dict != null && dict.get("receiver") != null){

            Integer receiver = Integer.valueOf(dict.get("receiver"));
            Integer sender = Integer.valueOf(dict.get("sender"));

            //check receiver is calling or not


            //accept or refuse
            if (Objects.equals(dict.get("message"), "1")){
                LambdaQueryWrapper<Chat> wrapper = Wrappers.<Chat>lambdaQuery().orderByAsc(Chat::getId);

                wrapper.like(Chat::getId, receiver); //current receiver is room id
                Chat result = chatEndpoint.chatMapper.selectOne(wrapper);
                if (!Objects.equals(result.getHolderone(), sender) && !Objects.equals(result.getHoldertwo(), sender)){
                    break out; //room not belong to sender
                }


                //get real receiver ID
                Integer targetReceiver;
                if (!Objects.equals(result.getHolderone(), sender)){
                    targetReceiver = result.getHolderone();
                }else {
                    targetReceiver = result.getHoldertwo();
                }

                //update database
                result.setCalling(1);
                chatEndpoint.chatMapper.updateById(result);

                String callMessage = MessageUtils.getMessage(false, false, dict.get(String.valueOf(targetReceiver)), 1);
                onlineSessions.get(targetReceiver).getBasicRemote().sendText(callMessage);



                break out;
            }else if(Objects.equals(dict.get("message"), "2")){
                LambdaQueryWrapper<Chat> wrapper = Wrappers.<Chat>lambdaQuery().orderByAsc(Chat::getId);

                wrapper.like(Chat::getId, receiver); //current receiver is room id
                Chat result = chatEndpoint.chatMapper.selectOne(wrapper);

                if (!Objects.equals(result.getHolderone(), sender) && !Objects.equals(result.getHoldertwo(), sender)){
                    break out; //room not belong to sender
                }

                //get real receiver ID
                Integer targetReceiver;
                if (!Objects.equals(result.getHolderone(), sender)){
                    targetReceiver = result.getHolderone();
                }else {
                    targetReceiver = result.getHoldertwo();
                }

                //set user call state become 0
                broadcastOffCall(result.getHolderone());
                broadcastOffCall(result.getHoldertwo());

                //update database
                result.setCalling(2);
                chatEndpoint.chatMapper.updateById(result);

                String callMessage = MessageUtils.getMessage(false, false, dict.get(String.valueOf(targetReceiver)), 2);
                onlineSessions.get(targetReceiver).getBasicRemote().sendText(callMessage);



                break out;
            }


            //check friend relationship
            LambdaQueryWrapper<FriendRelationship> friendwrapper = Wrappers.<FriendRelationship>lambdaQuery().orderByAsc(FriendRelationship::getId);

            friendwrapper.and(i -> i.like(FriendRelationship::getProfileid1, receiver).like(FriendRelationship::getProfileid2, sender)).
                    or(j -> j.and(i -> i.like(FriendRelationship::getProfileid1, sender).like(FriendRelationship::getProfileid2, receiver)));

            List<FriendRelationship> result = chatEndpoint.friendRelationshipMapper.selectList(friendwrapper);
            if (result.size() != 1){
                break out;
            }


            //check users' call state
            LambdaQueryWrapper<Profile> profileWrapper = Wrappers.<Profile>lambdaQuery().orderByAsc(Profile::getId);
            profileWrapper.like(Profile::getId, receiver);
            Profile profile = chatEndpoint.profileMapper.selectOne(profileWrapper);
            if(profile != null){
                if(profile.getOncall() == 1){
                    break out;
                }
            }else {
                break out;
            }

            LambdaQueryWrapper<Profile> profileWrapper2 = Wrappers.<Profile>lambdaQuery().orderByAsc(Profile::getId);
            profileWrapper2.like(Profile::getId, receiver);
            Profile profile2 = chatEndpoint.profileMapper.selectOne(profileWrapper);
            if(profile2 != null){
                if(profile2.getOncall() == 1){
                    break out;
                }
            }else {
                break out;
            }


            //build room
            Chat chat = new Chat();

            chat.setMessage(dict.get("message"));
            chat.setHolderone(Integer.valueOf(dict.get("sender")));
            chat.setHoldertwo(Integer.valueOf(dict.get("receiver")));
            chat.setCalling(0);
            chatEndpoint.chatMapper.insert(chat);

            //update profile oncall
            broadcastOnCall(receiver);
            broadcastOnCall(sender);

            //get room ID
            LambdaQueryWrapper<Chat> chatWrapper = Wrappers.<Chat>lambdaQuery().orderByDesc(Chat::getId);
            chatWrapper.and(i -> i.like(Chat::getHolderone, sender).like(Chat::getHoldertwo, receiver));
            List<Chat> rooms = chatEndpoint.chatMapper.selectList(chatWrapper);
            Integer roomID = rooms.get(0).getId();
//            Integer roomID = room.getId();

            String callMessage = MessageUtils.getMessage(false, true, dict.get("sender"), roomID);
            log.info("send room");
            onlineSessions.get(Integer.valueOf(dict.get("receiver"))).getBasicRemote().sendText(callMessage);


            String backMessage = MessageUtils.getMessage(false, true, dict.get("receiver"), roomID);
            session.getBasicRemote().sendText(backMessage);
        }


    }

    @OnClose
    public void onClose(@PathParam("profileID") String profileID) throws IOException {
        log.info("close session {}", profileID);

        broadcastOffline(Integer.valueOf(profileID));
        onlineSessions.remove(Integer.valueOf(profileID));
    }

    public Map<String, String> transferMessage(String message){
        message = message.replace("\"","");
        message = message.replace("{","");
        message = message.replace("}","");
        String[] split = message.split(",");
        Map<String, String> dict = new HashMap<>();

        for (String data : split){
            String[] split2 = data.split(":");
            if (Objects.equals(split2[0], "sender")){
                dict.put("sender", split2[1]);
            }else if(Objects.equals(split2[0], "receiver")){
                dict.put("receiver", split2[1]);
            }else if(Objects.equals(split2[0], "message")){
                dict.put("message", split2[1]);
            }else {
                return null;
            }
        }
        return dict;
    }


}
