package com.project.wisdomconnect.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.wisdomconnect.common.Result;
import com.project.wisdomconnect.entity.Profile;
import com.project.wisdomconnect.entity.User;
import com.project.wisdomconnect.mapper.ProfileMapper;
import com.project.wisdomconnect.mapper.UserMapper;
import com.project.wisdomconnect.utils.timeGetter;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Resource
    ProfileMapper profileMapper;
    @Resource
    UserMapper userMapper;

    @PostMapping
    public Result<?> save(@RequestBody Profile profile){
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId, profile.getOwner()));
        if (res == null) {
            return Result.error("-1", "user id not exist");
        }
        profileMapper.insert(profile);
        return Result.success();

    }

    //http://127.0.0.1:9090/user?pageNum=1&pageSize=1&query=
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String query){
        LambdaQueryWrapper<Profile> wrapper = Wrappers.<Profile>lambdaQuery().orderByAsc(Profile::getId);
        if (StrUtil.isNotBlank(query)) {
            wrapper.like(Profile::getUsername, query);
        }

        Page<Profile> profilePage = profileMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success(profilePage);
    }

}
