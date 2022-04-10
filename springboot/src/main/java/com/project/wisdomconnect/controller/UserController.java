package com.project.wisdomconnect.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.wisdomconnect.common.Result;
import com.project.wisdomconnect.controller.dto.UserDTO;
import com.project.wisdomconnect.entity.User;
import com.project.wisdomconnect.mapper.UserMapper;
//import com.project.wisdomconnect.service.UserService;
import com.project.wisdomconnect.service.UserService;
import com.project.wisdomconnect.utils.timeGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



import javax.annotation.Resource;

@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserService userService;

    @Resource
    UserMapper userMapper;
    //UserService userService = new UserService();

    @PostMapping
    public boolean save(@RequestBody User user){
        user.setRegisterTime(timeGetter.getCurrenTime());
        return userService.saveUser(user);

    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user){


        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword()));
        if(res == null){
            return Result.error("-1", "username or password invalid");
        }
        return Result.success();

    }
    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (res != null) {
            return Result.error("-1", "username has been register");
        }
        res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getEmail, user.getEmail()));
        if (res != null) {
            return Result.error("-1", "email has been register");
        }

        return Result.success();
    }

    //http://127.0.0.1:9090/user?pageNum=1&pageSize=1&query=
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String query){
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery().orderByAsc(User::getId);
        if (StrUtil.isNotBlank(query)) {
            wrapper.like(User::getUsername, query);
        }

        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success(userPage);
    }

    @PutMapping
    public Result<?> update(@RequestBody User user) {
        userMapper.updateById(user);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id) {
        userMapper.deleteById(id);
        return Result.success();
    }
}
