package com.project.wisdomconnect.common;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.project.wisdomconnect.entity.User;
import com.project.wisdomconnect.exception.ServiceException;
import com.project.wisdomconnect.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        // get token from request header
        String token = request.getHeader("token");

        if (StrUtil.isBlank(token)) {
            System.out.println(Constants.CODE_301_MESSAGE + "1");
            throw new ServiceException(Constants.CODE_301, Constants.CODE_301_MESSAGE);

        }

        // check user id in database
        Integer userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
        User user = userMapper.selectById(userId);
        if (user == null) {
            System.out.println(Constants.CODE_301_MESSAGE + "2");
            throw new ServiceException(Constants.CODE_301, Constants.CODE_301_MESSAGE);
        }
        // verify token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (Exception e) {
            System.out.println(Constants.CODE_301_MESSAGE + "3");
            throw new ServiceException(Constants.CODE_301, Constants.CODE_301_MESSAGE);
        }
        return true;
    }
}
