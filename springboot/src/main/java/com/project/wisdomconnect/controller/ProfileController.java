package com.project.wisdomconnect.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.wisdomconnect.common.Constants;
import com.project.wisdomconnect.common.Result;
import com.project.wisdomconnect.entity.Hobby;
import com.project.wisdomconnect.entity.Profile;
import com.project.wisdomconnect.entity.User;
import com.project.wisdomconnect.exception.ServiceException;
import com.project.wisdomconnect.mapper.ProfileMapper;
import com.project.wisdomconnect.mapper.UserMapper;
import cn.hutool.log.Log;
import com.project.wisdomconnect.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Resource
    ProfileMapper profileMapper;
    @Resource
    UserMapper userMapper;

    private static final Log LOG = Log.get();

    @PostMapping("/create")
    public Result<?> save(@RequestBody Profile profile){
        User user = TokenUtils.getUser();
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId, user.getId()));
        if (res == null) {
            return Result.error("-1", "user id not exist");
        }
        // check is there any duplicated data in database
        try{
            Profile res2 = profileMapper.selectOne(Wrappers.<Profile>lambdaQuery().eq(Profile::getUsername, profile.getUsername()));
            if (res2 != null) {
                return Result.error(Constants.CODE_408, Constants.CODE_408_MESSAGE);
            }
            User res3 = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId, profile.getOwner()));
            if (res3 == null) {
                return Result.error(Constants.CODE_402, Constants.CODE_402_MESSAGE);
            }

        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, Constants.CODE_500_MESSAGE);
        }



        if (!Objects.equals(user.getRole(), "manager")){
            profile.setOwner(user.getId());
        }

        LambdaQueryWrapper<Profile> wrapper = Wrappers.<Profile>lambdaQuery().orderByAsc(Profile::getId);
        wrapper.like(Profile::getOwner, profile.getOwner());
        List<Profile> profileList = profileMapper.selectList(wrapper);
        if(!Objects.equals(user.getRole(), "facility")){
            if (profileList.size() >= 1){
                return Result.error(Constants.CODE_409, Constants.CODE_409_MESSAGE);
            }
        }else {
            if (profileList.size() >= 7){
                return Result.error(Constants.CODE_409, Constants.CODE_409_MESSAGE);
            }
        }
        profileMapper.insert(profile);
        return Result.success();

    }

    //http://127.0.0.1:9090/user?pageNum=1&pageSize=1&query=
    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String query){
        User user = TokenUtils.getUser();
        LambdaQueryWrapper<Profile> wrapper = Wrappers.<Profile>lambdaQuery().orderByAsc(Profile::getId);
        if (StrUtil.isNotBlank(query)) {
            wrapper.like(Profile::getUsername, query);
        }

        Page<Profile> profilePage = profileMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success(profilePage);
    }

    @PutMapping
    public Result<?> update(@RequestBody Profile profile) {
        profileMapper.updateById(profile);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        profileMapper.deleteById(id);
        return Result.success();
    }
}
