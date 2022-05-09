package com.project.wisdomconnect.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.wisdomconnect.common.Result;
import com.project.wisdomconnect.entity.Hobby;
import com.project.wisdomconnect.entity.Profile;
import com.project.wisdomconnect.entity.User;
import com.project.wisdomconnect.mapper.HobbyMapper;
import com.project.wisdomconnect.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/hobby")
public class HobbyController {

    @Resource
    HobbyMapper hobbyMapper;

    @GetMapping("/page")
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

    @PostMapping("/create")
    public Result<?> save(@RequestBody Hobby hobby){
        User user = TokenUtils.getUser();
        if (hobby.getLevel() == 2){
            Hobby res = hobbyMapper.selectOne(Wrappers.<Hobby>lambdaQuery().eq(Hobby::getName, hobby.getTophobbyname()));
            if (res == null) {
                return Result.error("-1", "top hobby not exist");
            }
        }
        Hobby res = hobbyMapper.selectOne(Wrappers.<Hobby>lambdaQuery().eq(Hobby::getName, hobby.getName()));
        if (res != null) {
            return Result.error("-1", "hobby exist");
        }
        hobbyMapper.insert(hobby);
        return Result.success();

    }

    @PutMapping
    public Result<?> update(@RequestBody Hobby hobby) {
        hobbyMapper.updateById(hobby);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        hobbyMapper.deleteById(id);
        return Result.success();
    }
}
