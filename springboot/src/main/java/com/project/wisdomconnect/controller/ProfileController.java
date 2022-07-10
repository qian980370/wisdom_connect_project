package com.project.wisdomconnect.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.wisdomconnect.common.Constants;
import com.project.wisdomconnect.common.Result;
import com.project.wisdomconnect.entity.*;
import com.project.wisdomconnect.exception.ServiceException;
import com.project.wisdomconnect.mapper.*;
import cn.hutool.log.Log;
import com.project.wisdomconnect.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Resource
    ProfileMapper profileMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    BlockRelationshipMapper blockRelationshipMapper;
    @Resource
    FriendRelationshipMapper friendRelationshipMapper;
    @Resource
    HobbyRelationshipMapper hobbyRelationshipMapper;

    private static final Log LOG = Log.get();

    @PostMapping("/create")
    public Result<?> save(@RequestBody Profile profile){
        User user = TokenUtils.getUser();
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId, user.getId()));
        if (res == null) {
            return Result.error("-1", "user id not exist");
        }
        if (!Objects.equals(user.getRole(), "manager")){
            profile.setOwner(user.getId());
        }
        if (Objects.equals(user.getRole(), "manager") && profile.getOwner() == null){
            profile.setOwner(user.getId());
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

        LambdaQueryWrapper<Profile> wrapper = Wrappers.<Profile>lambdaQuery().orderByAsc(Profile::getId);
        wrapper.like(Profile::getOwner, profile.getOwner());
        List<Profile> profileList = profileMapper.selectList(wrapper);
        if(!Objects.equals(user.getRole(), "facility")){
            if (profileList.size() > 1){
                return Result.error(Constants.CODE_409, Constants.CODE_409_MESSAGE);
            }
        }else {
            if (profileList.size() > 7){
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
        wrapper.like(Profile::getOwner, user.getId());
        Page<Profile> profilePage = profileMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success(profilePage);
    }

    //http://127.0.0.1:9090/user?pageNum=1&pageSize=1&query=
    @GetMapping("/searchProfile")
    public Result<?> searchProfile(@RequestParam Integer profileID,
                              @RequestParam String query){
        User user = TokenUtils.getUser();
        LambdaQueryWrapper<Profile> profileWrapper = Wrappers.<Profile>lambdaQuery().orderByAsc(Profile::getId);
        if (StrUtil.isNotBlank(query)) {
            profileWrapper.like(Profile::getUsername, query);
        }
        profileWrapper.like(Profile::getPrivacy, 0);


        //check block
        LambdaQueryWrapper<BlockRelationship> blockWrapper = Wrappers.<BlockRelationship>lambdaQuery().orderByAsc(BlockRelationship::getId);
        blockWrapper.like(BlockRelationship::getProfileid, profileID).or().like(BlockRelationship::getBlockid, profileID);
        List<BlockRelationship> blockResult = blockRelationshipMapper.selectList(blockWrapper);

        Integer target;
        boolean isFirst = true;

        for (BlockRelationship f : blockResult){
            if (!Objects.equals(f.getProfileid(), profileID)){
                target = f.getProfileid();
            }else {
                target = f.getBlockid();
            }
            if (isFirst){
                profileWrapper.notLike(Profile::getId, target).and(i -> i.notLike(Profile::getId, profileID));
                isFirst = false;
            }else{
                final Integer res = target;
                profileWrapper.and(i -> i.notLike(Profile::getId, res));
            }
        }
        List<Profile> profileCollection;

        profileCollection = profileMapper.selectList(profileWrapper);

        return Result.success(profileCollection);
    }


    @GetMapping("/page-manager")
    public Result<?> findPageManager(@RequestParam(defaultValue = "1") Integer pageNum,
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

    //http://127.0.0.1:9090/user?pageNum=1&pageSize=1&query=
    @GetMapping("/friendList")
    public Result<?> getFriendList(@RequestParam Integer profileID){
        if (profileID == null){
            return Result.error(Constants.CODE_415, Constants.CODE_415_MESSAGE);
        }
        User user = TokenUtils.getUser();
        LambdaQueryWrapper<FriendRelationship> wrapper = Wrappers.<FriendRelationship>lambdaQuery().orderByAsc(FriendRelationship::getId);

        wrapper.like(FriendRelationship::getProfileid1, profileID).ne(FriendRelationship::getState, 0).or().like(FriendRelationship::getProfileid2, profileID).ne(FriendRelationship::getState, 0);
        List<FriendRelationship> result = friendRelationshipMapper.selectList(wrapper);

        LambdaQueryWrapper<Profile> profileWrapper = Wrappers.<Profile>lambdaQuery().orderByAsc(Profile::getId);

        boolean isFirst = true;
        Integer target = null;
        for (FriendRelationship f : result){
            if (!Objects.equals(f.getProfileid1(), profileID)){
                target = f.getProfileid1();
            }else {
                target = f.getProfileid2();
            }
            if (isFirst){
                profileWrapper.like(Profile::getId, target);
                isFirst = false;
            }else{
                profileWrapper.or().like(Profile::getId, target);
            }
        }
        List<Profile> friendsCollection = new ArrayList<>();
        if (result.size() > 0){
            friendsCollection = profileMapper.selectList(profileWrapper);
        }


        return Result.success(friendsCollection);
    }

    @GetMapping("/randomFriends")
    public Result<?> getRandomFriends(@RequestParam Integer profileID){
        if (profileID == null){
            return Result.error(Constants.CODE_415, Constants.CODE_415_MESSAGE);
        }
        User user = TokenUtils.getUser();
        LambdaQueryWrapper<FriendRelationship> wrapper = Wrappers.<FriendRelationship>lambdaQuery().orderByAsc(FriendRelationship::getId);

        wrapper.like(FriendRelationship::getProfileid1, profileID).or().like(FriendRelationship::getProfileid2, profileID);
        List<FriendRelationship> result = friendRelationshipMapper.selectList(wrapper);

        LambdaQueryWrapper<Profile> profileWrapper = Wrappers.<Profile>lambdaQuery().orderByAsc(Profile::getId);

        LambdaQueryWrapper<BlockRelationship> blockWrapper = Wrappers.<BlockRelationship>lambdaQuery().orderByAsc(BlockRelationship::getId);
        blockWrapper.like(BlockRelationship::getProfileid, profileID).or().like(BlockRelationship::getBlockid, profileID);
        List<BlockRelationship> blockResult = blockRelationshipMapper.selectList(blockWrapper);

        boolean isFirst = true;
        Integer target = null;
        //get
        for (FriendRelationship f : result){
            if (!Objects.equals(f.getProfileid1(), profileID)){
                target = f.getProfileid1();
            }else {
                target = f.getProfileid2();
            }
            if (isFirst){
                profileWrapper.notLike(Profile::getId, target).and(i -> i.notLike(Profile::getId, profileID));
                isFirst = false;
            }else{
                final Integer res = target;
                profileWrapper.and(i -> i.notLike(Profile::getId, res));
            }
        }

        for (BlockRelationship f : blockResult){
            if (!Objects.equals(f.getProfileid(), profileID)){
                target = f.getProfileid();
            }else {
                target = f.getBlockid();
            }
            if (isFirst){
                profileWrapper.notLike(Profile::getId, target).and(i -> i.notLike(Profile::getId, profileID));
                isFirst = false;
            }else{
                final Integer res = target;
                profileWrapper.and(i -> i.notLike(Profile::getId, res));
            }
        }
        profileWrapper.and(i -> i.notLike(Profile::getPrivacy, 1));
        // execute query
        List<Profile> friendsCollection = profileMapper.selectList(profileWrapper);
        List<Profile> randomFriendCollection = new ArrayList<>();
        List<Integer> tempList=new ArrayList<Integer>();
        // get result size
        int size = friendsCollection.size();
        // get no repeat random friends
        int randomFriendsNum = 3;
        if (size >= randomFriendsNum){
            for (int i = 0; i < randomFriendsNum; i++){
                int random = new Random().nextInt(size);
                if(!tempList.contains(random)){
                    tempList.add(random);
                    randomFriendCollection.add(friendsCollection.get(random));
                }else{
                    i--;
                }

            }
        }else {
            randomFriendCollection = friendsCollection;
        }


        return Result.success(randomFriendCollection);
    }


    @DeleteMapping("/friendDelete") //@RequestBody Profile profile
    public Result<?> friendDelete(@RequestBody RequestForm requestForm) {
        Integer profileID = requestForm.getProfileID();
        Integer targetID = requestForm.getTargetID();

        LambdaQueryWrapper<FriendRelationship> wrapper = Wrappers.<FriendRelationship>lambdaQuery().orderByAsc(FriendRelationship::getId);

        wrapper.and(i -> i.like(FriendRelationship::getProfileid1, profileID).like(FriendRelationship::getProfileid2, targetID)).
                or(j -> j.and(i -> i.like(FriendRelationship::getProfileid2, profileID).like(FriendRelationship::getProfileid1, targetID)));
        List<FriendRelationship> result = friendRelationshipMapper.selectList(wrapper);
        if (result.size() != 1){
            return Result.error(Constants.CODE_416, Constants.CODE_416_MESSAGE);
        }
        friendRelationshipMapper.deleteById(result.get(0).getId());
        return Result.success();
    }

    @PostMapping("/addFriend")
    public Result<?> addFriend(@RequestBody RequestForm requestForm){

        Integer profileID = requestForm.getProfileID();
        Integer targetID = requestForm.getTargetID();
        //checking for repeat request
        LambdaQueryWrapper<FriendRelationship> wrapper = Wrappers.<FriendRelationship>lambdaQuery().orderByAsc(FriendRelationship::getId);

        wrapper.and(i -> i.like(FriendRelationship::getProfileid1, profileID).like(FriendRelationship::getProfileid2, targetID)).
                or(j -> j.and(i -> i.like(FriendRelationship::getProfileid2, profileID).like(FriendRelationship::getProfileid1, targetID)));
        List<FriendRelationship> result = friendRelationshipMapper.selectList(wrapper);
        if (result.size() != 0){
            return Result.error(Constants.CODE_417, Constants.CODE_417_MESSAGE);
        }else{
            FriendRelationship friendRelationship = new FriendRelationship();
            friendRelationship.setProfileid1(profileID);
            friendRelationship.setProfileid2(targetID);
            friendRelationship.setState(0);
            friendRelationshipMapper.insert(friendRelationship);
        }

        return Result.success();
    }

    @PutMapping("/acceptFriendRequest")
    public Result<?> acceptFriendRequest(@RequestBody RequestForm requestForm){

        Integer profileID = requestForm.getProfileID();
        Integer targetID = requestForm.getTargetID();
        //checking for request exist
        LambdaQueryWrapper<FriendRelationship> wrapper = Wrappers.<FriendRelationship>lambdaQuery().orderByAsc(FriendRelationship::getId);

        wrapper.and(i -> i.like(FriendRelationship::getProfileid1, targetID).like(FriendRelationship::getProfileid2, profileID));
        List<FriendRelationship> result = friendRelationshipMapper.selectList(wrapper);
        if (result.size() != 1){
            return Result.error(Constants.CODE_416, Constants.CODE_416_MESSAGE);
        }else{
            FriendRelationship friendRelationship = result.get(0);
            friendRelationship.setState(1);
            friendRelationshipMapper.updateById(friendRelationship);
        }

        return Result.success();
    }

    @GetMapping("/friendsRequest")
    public Result<?> getFriendsRequest(@RequestParam Integer profileID){
        if (profileID == null){
            return Result.error(Constants.CODE_415, Constants.CODE_415_MESSAGE);
        }

        LambdaQueryWrapper<FriendRelationship> wrapper = Wrappers.<FriendRelationship>lambdaQuery().orderByAsc(FriendRelationship::getId);

        wrapper.like(FriendRelationship::getProfileid2, profileID).eq(FriendRelationship::getState, 0);
        List<FriendRelationship> result = friendRelationshipMapper.selectList(wrapper);

        LambdaQueryWrapper<Profile> profileWrapper = Wrappers.<Profile>lambdaQuery().orderByAsc(Profile::getId);

        boolean isFirst = true;
        Integer target = null;
        for (FriendRelationship f : result){
            if (!Objects.equals(f.getProfileid1(), profileID)){
                target = f.getProfileid1();
            }else {
                target = f.getProfileid2();
            }
            if (isFirst){
                profileWrapper.like(Profile::getId, target);
                isFirst = false;
            }else{
                profileWrapper.or().like(Profile::getId, target);
            }
        }

        List<Profile> friendsCollection = new ArrayList<>();
        if (result.size() > 0){
            friendsCollection = profileMapper.selectList(profileWrapper);
        }


        return Result.success(friendsCollection);
    }

    @PostMapping("/blockUser")
    public Result<?> blockUser(@RequestBody RequestForm requestForm){

        Integer profileID = requestForm.getProfileID();
        Integer targetID = requestForm.getTargetID();
        //checking for repeat request
        LambdaQueryWrapper<FriendRelationship> wrapper = Wrappers.<FriendRelationship>lambdaQuery().orderByAsc(FriendRelationship::getId);

        wrapper.and(i -> i.like(FriendRelationship::getProfileid1, profileID).like(FriendRelationship::getProfileid2, targetID)).
                or(j -> j.and(i -> i.like(FriendRelationship::getProfileid2, profileID).like(FriendRelationship::getProfileid1, targetID)));
        List<FriendRelationship> result = friendRelationshipMapper.selectList(wrapper);
        if (result.size() != 0){
            LambdaQueryWrapper<FriendRelationship> deleteWrapper = Wrappers.<FriendRelationship>lambdaQuery().orderByAsc(FriendRelationship::getId);

            deleteWrapper.and(i -> i.like(FriendRelationship::getProfileid1, profileID).like(FriendRelationship::getProfileid2, targetID)).
                    or(j -> j.and(i -> i.like(FriendRelationship::getProfileid2, profileID).like(FriendRelationship::getProfileid1, targetID)));
            List<FriendRelationship> deleteResult = friendRelationshipMapper.selectList(deleteWrapper);
            if (deleteResult.size() != 1){
                return Result.error(Constants.CODE_416, Constants.CODE_416_MESSAGE);
            }
            friendRelationshipMapper.deleteById(deleteResult.get(0).getId());
        }
        BlockRelationship blockRelationship = new BlockRelationship();
        blockRelationship.setProfileid(profileID);
        blockRelationship.setBlockid(targetID);
        blockRelationshipMapper.insert(blockRelationship);


        return Result.success();
    }

    @DeleteMapping("/unBlock") //@RequestBody Profile profile
    public Result<?> unBlock(@RequestBody RequestForm requestForm) {
        Integer profileID = requestForm.getProfileID();
        Integer targetID = requestForm.getTargetID();

        LambdaQueryWrapper<BlockRelationship> wrapper = Wrappers.<BlockRelationship>lambdaQuery().orderByAsc(BlockRelationship::getId);

        wrapper.and(i -> i.like(BlockRelationship::getProfileid, profileID).like(BlockRelationship::getBlockid, targetID));

        List<BlockRelationship> result = blockRelationshipMapper.selectList(wrapper);
        if (result.size() != 1){
            return Result.error(Constants.CODE_416, Constants.CODE_416_MESSAGE);
        }
        blockRelationshipMapper.deleteById(result.get(0).getId());
        return Result.success();
    }

    @GetMapping("/blockList")
    public Result<?> getBlockList(@RequestParam Integer profileID){
        if (profileID == null){
            return Result.error(Constants.CODE_415, Constants.CODE_415_MESSAGE);
        }
        User user = TokenUtils.getUser();
        LambdaQueryWrapper<BlockRelationship> wrapper = Wrappers.<BlockRelationship>lambdaQuery().orderByAsc(BlockRelationship::getId);

        wrapper.like(BlockRelationship::getProfileid, profileID);
        List<BlockRelationship> result = blockRelationshipMapper.selectList(wrapper);

        LambdaQueryWrapper<Profile> profileWrapper = Wrappers.<Profile>lambdaQuery().orderByAsc(Profile::getId);

        boolean isFirst = true;
        Integer target = null;
        for (BlockRelationship f : result){
            target = f.getBlockid();
            if (isFirst){
                profileWrapper.like(Profile::getId, target);
                isFirst = false;
            }else{
                profileWrapper.or().like(Profile::getId, target);
            }
        }
        List<Profile> blockCollection = new ArrayList<>();
        if (result.size() > 0){
            blockCollection = profileMapper.selectList(profileWrapper);
        }


        return Result.success(blockCollection);
    }

    @PutMapping("/updatePrivacy")
    public Result<?> updatePrivacy(@RequestBody RequestForm requestForm) {

        Integer profileID = requestForm.getProfileID();

        //checking for request exist
        LambdaQueryWrapper<Profile> wrapper = Wrappers.<Profile>lambdaQuery().orderByAsc(Profile::getId);

        wrapper.like(Profile::getId, profileID);
        List<Profile> result = profileMapper.selectList(wrapper);
        Profile profile;
        if (result.size() != 1) {
            return Result.error(Constants.CODE_416, Constants.CODE_416_MESSAGE);
        } else {
            profile = result.get(0);
            int privacy = profile.getPrivacy();
            if (privacy == 1) {
                profile.setPrivacy(0);
            } else {
                profile.setPrivacy(1);
            }
            profileMapper.updateById(profile);
        }

        return Result.success(profile);
    }
}
