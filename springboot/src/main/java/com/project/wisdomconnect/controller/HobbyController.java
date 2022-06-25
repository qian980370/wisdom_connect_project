package com.project.wisdomconnect.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.wisdomconnect.common.Constants;
import com.project.wisdomconnect.common.Result;
import com.project.wisdomconnect.entity.*;
import com.project.wisdomconnect.mapper.HobbyMapper;
import com.project.wisdomconnect.mapper.HobbyRelationshipMapper;
import com.project.wisdomconnect.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/hobby")
public class HobbyController {

    @Resource
    HobbyMapper hobbyMapper;
    @Resource
    HobbyRelationshipMapper hobbyRelationshipMapper;

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

    @GetMapping("/hobbyList")
    public Result<?> getHobbyList(@RequestParam Integer profileID){
        if (profileID == null){
            return Result.error(Constants.CODE_415, Constants.CODE_415_MESSAGE);
        }
        User user = TokenUtils.getUser();
        LambdaQueryWrapper<HobbyRelationship> wrapper = Wrappers.<HobbyRelationship>lambdaQuery().orderByAsc(HobbyRelationship::getId);

        wrapper.like(HobbyRelationship::getProfileid, profileID);
        List<HobbyRelationship> result = hobbyRelationshipMapper.selectList(wrapper);

        LambdaQueryWrapper<Hobby> hobbyWrapper = Wrappers.<Hobby>lambdaQuery().orderByAsc(Hobby::getId);

        boolean isFirst = true;
        Integer target = null;
        for (HobbyRelationship f : result){
            target = f.getHobbyid();
            if (isFirst){
                hobbyWrapper.like(Hobby::getId, target);
                isFirst = false;
            }else{
                hobbyWrapper.or().like(Hobby::getId, target);
            }
        }
        List<Hobby> hobbyCollection = new ArrayList<>();
        if (result.size() > 0){
            hobbyCollection = hobbyMapper.selectList(hobbyWrapper);
        }


        return Result.success(hobbyCollection);
    }

    @GetMapping("/randomHobbies")
    public Result<?> getRandomHobbies(@RequestParam Integer profileID){
        if (profileID == null){
            return Result.error(Constants.CODE_415, Constants.CODE_415_MESSAGE);
        }
        User user = TokenUtils.getUser();
        LambdaQueryWrapper<HobbyRelationship> wrapper = Wrappers.<HobbyRelationship>lambdaQuery().orderByAsc(HobbyRelationship::getId);

        wrapper.like(HobbyRelationship::getProfileid, profileID);
        List<HobbyRelationship> result = hobbyRelationshipMapper.selectList(wrapper);

        boolean isFirst = true;
        Integer target = null;
        //get
        LambdaQueryWrapper<Hobby> hobbyWrapper = Wrappers.<Hobby>lambdaQuery().orderByAsc(Hobby::getId);
        for (HobbyRelationship f : result){
            target = f.getHobbyid();
            if (isFirst){
                hobbyWrapper.notLike(Hobby::getId, target);
                isFirst = false;
            }else{
                final Integer res = target;
                hobbyWrapper.and(i -> i.notLike(Hobby::getId, res));
            }
        }
        // execute query
        List<Hobby> hobbyCollection = hobbyMapper.selectList(hobbyWrapper);
        List<Hobby> randomHobbyCollection = new ArrayList<>();
        List<Integer> tempList=new ArrayList<Integer>();
        // get result size
        int size = hobbyCollection.size();
        // get no repeat random friends
        int randomFriendsNum = 3;
        if (size >= randomFriendsNum){
            for (int i = 0; i < randomFriendsNum; i++){
                int random = new Random().nextInt(size);
                if(!tempList.contains(random)){
                    tempList.add(random);
                    randomHobbyCollection.add(hobbyCollection.get(random));
                }else{
                    i--;
                }

            }
        }else {
            randomHobbyCollection = hobbyCollection;
        }


        return Result.success(randomHobbyCollection);
    }

    @PostMapping("/addHobby")
    public Result<?> addFriend(@RequestBody RequestForm requestForm){

        Integer profileID = requestForm.getProfileID();
        Integer targetID = requestForm.getTargetID();
        //checking for repeat request
        LambdaQueryWrapper<HobbyRelationship> wrapper = Wrappers.<HobbyRelationship>lambdaQuery().orderByAsc(HobbyRelationship::getId);

        wrapper.and(i -> i.like(HobbyRelationship::getProfileid, profileID).like(HobbyRelationship::getHobbyid, targetID));
        List<HobbyRelationship> result = hobbyRelationshipMapper.selectList(wrapper);
        if (result.size() != 0){
            return Result.error(Constants.CODE_417, Constants.CODE_417_MESSAGE);
        }else{
            HobbyRelationship hobbyRelationship = new HobbyRelationship();
            hobbyRelationship.setProfileid(profileID);
            hobbyRelationship.setHobbyid(targetID);
            hobbyRelationshipMapper.insert(hobbyRelationship);
        }

        return Result.success();
    }

    @DeleteMapping("/hobbyDelete") //@RequestBody Profile profile
    public Result<?> hobbyDelete(@RequestBody RequestForm requestForm) {
        Integer profileID = requestForm.getProfileID();
        Integer targetID = requestForm.getTargetID();

        LambdaQueryWrapper<HobbyRelationship> wrapper = Wrappers.<HobbyRelationship>lambdaQuery().orderByAsc(HobbyRelationship::getId);

        wrapper.and(i -> i.like(HobbyRelationship::getProfileid, profileID).like(HobbyRelationship::getHobbyid, targetID));
        List<HobbyRelationship> result = hobbyRelationshipMapper.selectList(wrapper);
        if (result.size() != 1){
            return Result.error(Constants.CODE_416, Constants.CODE_416_MESSAGE);
        }
        hobbyRelationshipMapper.deleteById(result.get(0).getId());

        return Result.success();
    }
}
