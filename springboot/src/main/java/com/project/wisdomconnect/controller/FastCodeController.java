package com.project.wisdomconnect.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.wisdomconnect.common.Constants;
import com.project.wisdomconnect.common.Result;
import com.project.wisdomconnect.entity.FastCode;
import com.project.wisdomconnect.entity.Profile;
import com.project.wisdomconnect.entity.User;
import com.project.wisdomconnect.exception.ServiceException;
import com.project.wisdomconnect.mapper.FastCodeMapper;
import com.project.wisdomconnect.mapper.ProfileMapper;
import com.project.wisdomconnect.mapper.UserMapper;
import com.project.wisdomconnect.utils.MailUtils;
import com.project.wisdomconnect.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/fastcode")
public class FastCodeController {
    @Resource
    FastCodeMapper fastCodeMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    ProfileMapper profileMapper;


    private static final Log LOG = Log.get();

    @PostMapping("/create")
    public Result<?> save(@RequestBody FastCode fastCode){
        User user = TokenUtils.getUser();
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId, user.getId()));
        if (res == null) {
            return Result.error("-1", "user id not exist");
        }

        // check is there any duplicated data in database
        try{
            User res2 = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, fastCode.getName()));
            if (res2 != null) {
                return Result.error(Constants.CODE_402, Constants.CODE_402_MESSAGE);
            }
            res2 = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getEmail, fastCode.getEmail()));
            if (res2 != null) {
                return Result.error(Constants.CODE_403, Constants.CODE_403_MESSAGE);
            }
            res2 = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getAbn, fastCode.getAbn()));
            if (res2 != null) {
                return Result.error(Constants.CODE_410, Constants.CODE_410_MESSAGE);
            }

        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, Constants.CODE_500_MESSAGE);
        }

//        if(user.getRole() != null){
//            if (!Objects.equals(user.getRole(), "manager")){
//                return Result.error(Constants.CODE_401, Constants.CODE_401_MESSAGE);
//            }
//        }

        if(fastCode.getRole() != null){
            if (!Objects.equals(fastCode.getRole(), "facility")){
                return Result.error(Constants.CODE_401, Constants.CODE_401_MESSAGE);
            }
        }

        if(fastCode.getAddress() == null || fastCode.getAbn() == null || fastCode.getName() == null || fastCode.getEmail() == null){
            return Result.error(Constants.CODE_411, Constants.CODE_411_MESSAGE);
        }


        FastCode insertFastCode = new FastCode();
        insertFastCode.setAbn(fastCode.getAbn());
        insertFastCode.setName(fastCode.getName());
        insertFastCode.setAddress(fastCode.getAddress());
        insertFastCode.setRole("facility");

        String md5 = SecureUtil.md5(fastCode.getName() + fastCode.getEmail() + fastCode.getAddress() + fastCode.getAbn());
        List<FastCode> findcode = fastCodeMapper.selectList(Wrappers.<FastCode>lambdaQuery().eq(FastCode::getMd5, md5));
        if (findcode.size() != 0) {
            return Result.error(Constants.CODE_414, Constants.CODE_414_MESSAGE);
        }
        insertFastCode.setMd5(md5);

        if (Objects.equals(user.getRole(), "manager")){
            insertFastCode.setCode(fastCode.getCode());
        }

        insertFastCode.setEmail(fastCode.getEmail());

        fastCodeMapper.insert(insertFastCode);
        String[] reveiver = new String[1];
        reveiver[0] = "qian980730@gmail.com";
        MailUtils.sendFromGMail(reveiver,"New Facility Application",
                "New Facility Application from " + insertFastCode.getName() + " with abn code: "  + insertFastCode.getAbn() + "\nplease check in Manager System");
        return Result.success();

    }

    //http://127.0.0.1:9090/user?pageNum=1&pageSize=1&query=
    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String query){
        User user = TokenUtils.getUser();
        LambdaQueryWrapper<FastCode> wrapper = Wrappers.<FastCode>lambdaQuery().orderByAsc(FastCode::getId);
        if (StrUtil.isNotBlank(query)) {
            wrapper.like(FastCode::getName, query);
        }

        Page<FastCode> fastCodePage = fastCodeMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success(fastCodePage);
    }

    @PutMapping
    public Result<?> update(@RequestBody FastCode fastCode) {
        User user = TokenUtils.getUser();
        assert user != null;
        fastCode.setManager(user.getId());

        if (fastCode.getCode() != null){
            fastCode.setState(Constants.fastCode_Set);
            String[] reveiver = new String[1];
            reveiver[0] = fastCode.getEmail();
            MailUtils.sendFromGMail(reveiver,"WisdomConnect This is Your Fast Code",
                    "Thanks for you patient for waiting fast code, you fast code is show in below, please input it " +
                            "when you register user account. \n" + fastCode.getCode() +
                    "\nBe attention, your account information should match the form which you upload before for getting " +
                            "fast code below is your fast code information receipt:\n" + "username: " + fastCode.getName() + "\nemail: " + fastCode.getEmail()
                            + "\naddress: " + fastCode.getAddress() + "\nABN: " + fastCode.getAbn());
        }else{
            fastCode.setState(Constants.fastCode_Wait);
        }

        String md5 = SecureUtil.md5(fastCode.getName() + fastCode.getEmail() + fastCode.getAddress() + fastCode.getAbn());
        fastCode.setMd5(md5);
        fastCodeMapper.updateById(fastCode);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        fastCodeMapper.deleteById(id);
        return Result.success();
    }


}
