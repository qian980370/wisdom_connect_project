package com.project.wisdomconnect.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.wisdomconnect.entity.Hobby;
import com.project.wisdomconnect.mapper.HobbyMapper;
import com.project.wisdomconnect.service.inter.HobbyServiceInterface;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class HobbyService extends ServiceImpl<HobbyMapper, Hobby> implements HobbyServiceInterface {
    @Resource
    HobbyMapper hobbyMapper;


}
