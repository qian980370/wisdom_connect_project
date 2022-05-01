package com.project.wisdomconnect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.wisdomconnect.entity.Profile;
import com.project.wisdomconnect.entity.User;
import com.project.wisdomconnect.mapper.ProfileMapper;
import com.project.wisdomconnect.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class WisdomconnectApplicationTests {

	@Test
	void contextLoads() {
	}

}

class MyBatisPlusTests{
	@Resource
	UserMapper userMapper;
	ProfileMapper profileMapper;

	@Test
	public void test1(){
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.like("username","e");
		List<User> list = userMapper.selectList(queryWrapper);
		list.forEach(System.out::println);
	}
	@Test
	public void test2(){
		QueryWrapper<Profile> queryWrapper = new QueryWrapper<>();
		queryWrapper.like("username","j")
					.between("age",35,60);
		List<Profile> list = profileMapper.selectList(queryWrapper);
		list.forEach(System.out::println);
	}
}
