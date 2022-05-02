package com.project.wisdomconnect.service.inter;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.wisdomconnect.common.Result;
import com.project.wisdomconnect.controller.dto.UserDTO;
import com.project.wisdomconnect.entity.User;

public interface UserServiceInterface extends IService<User> {
    UserDTO login(UserDTO userDTO);

    Result<?> register(User user);

}
