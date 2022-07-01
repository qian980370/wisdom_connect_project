package com.project.wisdomconnect.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.wisdomconnect.common.Constants;
import com.project.wisdomconnect.common.Result;
import com.project.wisdomconnect.controller.dto.UserDTO;
import com.project.wisdomconnect.entity.FastCode;
import com.project.wisdomconnect.entity.User;
import com.project.wisdomconnect.exception.ServiceException;
import com.project.wisdomconnect.mapper.FastCodeMapper;
import com.project.wisdomconnect.mapper.UserMapper;
import com.project.wisdomconnect.service.inter.UserServiceInterface;
import com.project.wisdomconnect.utils.TimeGetter;
import com.project.wisdomconnect.utils.TokenUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;


@Service
public class UserService extends ServiceImpl<UserMapper, User> implements UserServiceInterface {
    @Resource
    UserMapper userMapper;

    @Resource
    FastCodeMapper fastCodeMapper;

    private static final Log LOG = Log.get();

    @Override
    public Result<?> register(@RequestBody User user){
        // check is there any duplicated data in database
        try{
            User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
            if (res != null) {
                return Result.error(Constants.CODE_402, Constants.CODE_402_MESSAGE);
            }
            res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getEmail, user.getEmail()));
            if (res != null) {
                return Result.error(Constants.CODE_403, Constants.CODE_403_MESSAGE);
            }
            res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getAbn, user.getAbn()));
            if (res != null) {
                return Result.error(Constants.CODE_410, Constants.CODE_410_MESSAGE);
            }

        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, Constants.CODE_500_MESSAGE);
        }

        // build new user
        User userInfo = new User();
        userInfo.setRegisterTime(TimeGetter.getCurrenTime());
        userInfo.setUsername(user.getUsername());
        userInfo.setEmail(user.getEmail());
        userInfo.setPassword(user.getPassword());

        // if register user is a facility, user should provide the abn
        if (!Objects.equals(user.getRole(), "facility")){
            if (user.getAbn() != null){
                return Result.error(Constants.CODE_407, Constants.CODE_407_MESSAGE);
            }
        }else {
            FastCode fastCode = fastCodeMapper.selectOne(Wrappers.<FastCode>lambdaQuery().eq(FastCode::getCode, user.getCode()));
            if (fastCode == null) {
                return Result.error(Constants.CODE_412, Constants.CODE_412_MESSAGE);
            }

            String md5 = SecureUtil.md5(user.getUsername() + user.getEmail() + user.getAddress() + user.getAbn());
            if (!Objects.equals(fastCode.getMd5(), md5)) {
                return Result.error(Constants.CODE_413, Constants.CODE_413_MESSAGE);
            }
            fastCode.setState(Constants.fastCode_Done);
            userInfo.setAbn(user.getAbn());
            fastCodeMapper.updateById(fastCode);
        }
        // check the role
        if (!Objects.equals(user.getRole(), "manager") && !Objects.equals(user.getRole(), "facility")
                && !Objects.equals(user.getRole(), "nurse") && !Objects.equals(user.getRole(), "resident")){
            return Result.error(Constants.CODE_405, Constants.CODE_405_MESSAGE);
        }
        userInfo.setRole(user.getRole());
        userInfo.setAddress(user.getAddress());
        save(userInfo);

        return Result.success();
    }

    @Override
    public UserDTO login(@RequestBody UserDTO userDTO){
        User user = getUserInfo(userDTO);

        if (user != null){
            // copy query result into userDTO
            BeanUtil.copyProperties(user, userDTO, true);

            // set token
            String token = TokenUtils.genToken(user.getId().toString(), user.getPassword());
            userDTO.setToken(token);


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
