package com.project.wisdomconnect.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.wisdomconnect.common.Result;
import com.project.wisdomconnect.entity.User;
import com.project.wisdomconnect.mapper.UserMapper;
import com.project.wisdomconnect.utils.timeGetter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Wrapper;


@Service
public class UserService {
    @Resource
    UserMapper userMapper;

    @PostMapping
    public Result<?> userInsert(@RequestBody User user){

        if(user.getId() == null){

        }

        user.setRegisterTime(timeGetter.getCurrenTime());
        userMapper.insert(user);
        return Result.success();
    }

    //http://127.0.0.1:9090/user?pageNum=1&pageSize=1&query=
    @GetMapping
    public Result<?> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String query){
        userMapper.selectPage(new Page<>(pageNum,pageSize), Wrappers.<User>lambdaQuery().like(User::getUsername, query));
        return Result.success();
    }



}
