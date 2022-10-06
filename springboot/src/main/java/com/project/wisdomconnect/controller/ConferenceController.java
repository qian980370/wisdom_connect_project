package com.project.wisdomconnect.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.project.wisdomconnect.common.Constants;
import com.project.wisdomconnect.common.Result;
import com.project.wisdomconnect.entity.*;
import com.project.wisdomconnect.mapper.ChatMapper;
import com.project.wisdomconnect.mapper.ConferenceMapper;
import com.project.wisdomconnect.mapper.ProfileMapper;
import com.project.wisdomconnect.utils.TokenUtils;
import org.apache.ibatis.jdbc.Null;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/conference")
public class ConferenceController {

    @Resource
    ConferenceMapper conferenceMapper;


    //http://127.0.0.1:9090/user?pageNum=1&pageSize=1&query=
    @GetMapping("/getRoom")
    public Result<?> searchRoom(@RequestParam Integer profileID){

        LambdaQueryWrapper<Conference> conferenceWrapper = Wrappers.<Conference>lambdaQuery().orderByAsc(Conference::getId);
//        if (StrUtil.isNotBlank(query)) {
//            conferenceWrapper.like(Conference::getConferenceID, query);
//        }
//        conferenceWrapper.clear();
        conferenceWrapper.like(Conference::getTenant, profileID);
        List<Conference> result = conferenceMapper.selectList(conferenceWrapper);
        if (result.size()!=0){//if current profile has own a room
            return Result.error(Constants.CODE_419, Constants.CODE_419_MESSAGE);
        }
        conferenceWrapper.clear();


        conferenceWrapper.like(Conference::getAvailable, 1);


        //check room available
        result = conferenceMapper.selectList(conferenceWrapper);

        if (result.size() < 1){
            return Result.error(Constants.CODE_418, Constants.CODE_418_MESSAGE);
        }

        Conference availableRoom = result.get(0);
        availableRoom.setAvailable(0);
        availableRoom.setTenant(profileID);
        conferenceMapper.updateById(availableRoom);

        return Result.success(availableRoom);
    }


    @PostMapping("/releaseRoom")
    public Result<?> releaseRoom(@RequestBody RequestForm requestForm){
        Integer profileID = requestForm.getProfileID();
        LambdaQueryWrapper<Conference> conferenceWrapper = Wrappers.<Conference>lambdaQuery().orderByAsc(Conference::getId);
//        if (StrUtil.isNotBlank(query)) {
//            conferenceWrapper.like(Conference::getConferenceID, query);
//        }
//        conferenceWrapper.clear();

        User user = TokenUtils.getUser();

        System.out.println(profileID);
        conferenceWrapper.like(Conference::getTenant, profileID);
        List<Conference> result = conferenceMapper.selectList(conferenceWrapper);
        if (result.size()!=0){//if current profile has own a room
            for (Conference conference: result){
                conference.setTenant(null);
                conference.setAvailable(1);
                conferenceMapper.updateById(conference);
            }
        }else {
            //return Result.error(Constants.CODE_420,Constants.CODE_420_MESSAGE);
        }



        return Result.success();
    }
}
