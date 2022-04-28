package com.project.wisdomconnect.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.wisdomconnect.common.Result;
import com.project.wisdomconnect.entity.Hobby;
import com.project.wisdomconnect.entity.Profile;
import com.project.wisdomconnect.mapper.HobbyMapper;
import com.project.wisdomconnect.mapper.ProfileMapper;
import com.project.wisdomconnect.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/hobby")
public class HobbyController {

    @Resource
    HobbyMapper hobbyMapper;

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String query){
        LambdaQueryWrapper<Hobby> wrapper = Wrappers.<Hobby>lambdaQuery().orderByAsc(Hobby::getId);
        if (StrUtil.isNotBlank(query)) {
            wrapper.like(Hobby::getName, query);
        }

        Page<Hobby> hobbyPage = hobbyMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success(hobbyPage);
    }
}
