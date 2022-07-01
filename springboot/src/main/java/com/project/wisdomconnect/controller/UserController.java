package com.project.wisdomconnect.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.wisdomconnect.common.Constants;
import com.project.wisdomconnect.common.Result;
import com.project.wisdomconnect.controller.dto.UserDTO;
import com.project.wisdomconnect.entity.User;
import com.project.wisdomconnect.exception.ServiceException;
import com.project.wisdomconnect.mapper.UserMapper;
//import com.project.wisdomconnect.service.UserService;
import com.project.wisdomconnect.service.UserService;
import com.project.wisdomconnect.utils.TimeGetter;
import com.project.wisdomconnect.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;


import javax.annotation.Resource;
import java.util.Objects;

@RestController
@RequestMapping("/user")

public class UserController {
    private static final Log LOG = Log.get();

    @Autowired
    private UserService userService;

    @Resource
    UserMapper userMapper;
    //UserService userService = new UserService();

    @PostMapping
    public boolean save(@RequestBody User user){
        user.setRegisterTime(TimeGetter.getCurrenTime());
        return userService.saveUser(user);

    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();


        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400, Constants.CODE_400_MESSAGE);
        }

        User user;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());

        try{
            user = userMapper.selectOne(queryWrapper);
        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, Constants.CODE_500_MESSAGE);
        }

//        if (!Objects.equals(user.getRole(), "manager")){
//            return Result.error(Constants.CODE_401, Constants.CODE_401_MESSAGE);
//        }

        UserDTO dto = userService.login(userDTO);
        dto.setPassword(null);
        return Result.success(dto);
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        return userService.register(user);
    }





    //http://127.0.0.1:9090/user?pageNum=1&pageSize=1&query=
    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String query){
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery().orderByAsc(User::getId);
        if (StrUtil.isNotBlank(query)) {
            wrapper.like(User::getUsername, query);
        }

        User checkUser = TokenUtils.getUser();
        System.out.println("current user is: " + checkUser.getUsername());

        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success(userPage);
    }

    @PutMapping
    public Result<?> update(@RequestBody User user) {
        userMapper.updateById(user);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        userMapper.deleteById(id);
        return Result.success();
    }
}
