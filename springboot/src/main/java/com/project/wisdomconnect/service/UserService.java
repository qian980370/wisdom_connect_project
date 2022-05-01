package com.project.wisdomconnect.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.wisdomconnect.common.Constants;
import com.project.wisdomconnect.common.Result;
import com.project.wisdomconnect.controller.dto.UserDTO;
import com.project.wisdomconnect.entity.User;
import com.project.wisdomconnect.exception.MyExceptionHandler;
import com.project.wisdomconnect.exception.ServiceException;
import com.project.wisdomconnect.mapper.UserMapper;
import com.project.wisdomconnect.service.inter.UserServiceInterface;
import com.project.wisdomconnect.utils.timeGetter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Wrapper;


@Service
public class UserService extends ServiceImpl<UserMapper, User> implements UserServiceInterface {
    @Resource
    UserMapper userMapper;

    private static final Log LOG = Log.get();

    @Override
    public Result<?> register(@RequestBody User user){

        try{
            User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
            if (res != null) {
                return Result.error(Constants.CODE_402, Constants.CODE_402_MESSAGE);
            }
            res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getEmail, user.getEmail()));
            if (res != null) {
                return Result.error(Constants.CODE_403, Constants.CODE_403_MESSAGE);
            }
        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, Constants.CODE_500_MESSAGE);
        }


        return Result.success();
    }

    @Override
    public UserDTO userLogin(@RequestBody UserDTO userDTO){
        User user = getUserInfo(userDTO);

        if (user != null){
            BeanUtil.copyProperties(user, userDTO, true);
            return userDTO;
        }else {
            throw new ServiceException(Constants.CODE_400, Constants.CODE_400_MESSAGE);
        }
    }

    private User getUserInfo(UserDTO userDTO){
        User user;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());

        try{
            user = getOne(queryWrapper);
        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, Constants.CODE_500_MESSAGE);
        }

        return user;
    }


    //http://127.0.0.1:9090/user?pageNum=1&pageSize=1&query=
    @GetMapping
    public Result<?> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String query){
        userMapper.selectPage(new Page<>(pageNum,pageSize), Wrappers.<User>lambdaQuery().like(User::getUsername, query));
        return Result.success();
    }


    public boolean saveUser(User user) {
        if(user.getId()==null){
            return save(user);
        }else{
            return updateById(user);
        }
    }
}
