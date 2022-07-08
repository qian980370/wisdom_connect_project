package com.project.wisdomconnect.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.project.wisdomconnect.common.Constants;
import com.project.wisdomconnect.common.Result;
import com.project.wisdomconnect.entity.Chat;
import com.project.wisdomconnect.entity.FriendRelationship;
import com.project.wisdomconnect.entity.Profile;
import com.project.wisdomconnect.entity.User;
import com.project.wisdomconnect.mapper.ChatMapper;
import com.project.wisdomconnect.utils.TokenUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/chatcontroller")
public class ChatController {
    @Resource
    ChatMapper chatMapper;

    @GetMapping("/room")
    public Result<?> getRoom(@RequestParam Integer roomID){
        if (roomID == null){
            return Result.error(Constants.CODE_415, Constants.CODE_415_MESSAGE);
        }

        LambdaQueryWrapper<Chat> wrapper = Wrappers.<Chat>lambdaQuery().orderByAsc(Chat::getId);

        wrapper.like(Chat::getId, roomID);
        Chat result = chatMapper.selectOne(wrapper);

        return Result.success(result);
    }

}
