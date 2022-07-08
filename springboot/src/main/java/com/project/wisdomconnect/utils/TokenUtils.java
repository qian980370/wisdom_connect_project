package com.project.wisdomconnect.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.project.wisdomconnect.entity.User;
import com.project.wisdomconnect.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@Component
public class TokenUtils {

    // import user mapper
    @Resource
    private UserMapper userMapper;

    private static UserMapper staticUserMapper;

    //after build construct
    @PostConstruct
    public void init() {
        staticUserMapper = userMapper;
    }

    /**
     * generate token
     * @return
     */
    public static String genToken(String userid, String password) {
        // after 10080 minutes(7 days) token will expire
        int expireTime = 60 * 24 * 7;
        return JWT.create().withExpiresAt(DateUtil.offsetMinute(new Date(), expireTime)).withAudience(userid)
                .sign(Algorithm.HMAC256(password));
    }

    /**
     * get user information for backend
     * @return
     */
    public static User getUser() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)){
                String aud = JWT.decode(token).getAudience().get(0);
                Integer userId = Integer.valueOf(aud);
                return staticUserMapper.selectById(userId);
            }else {
                return null;
            }

        } catch (Exception e) {
            log.error("analysis token fail", e);
            return null;
        }
    }

    /**
     * get user information for backend
     * @return
     */
    public static User decodeToken(String token) {
        try {
            if (StrUtil.isNotBlank(token)){
                String aud = JWT.decode(token).getAudience().get(0);
                Integer userId = Integer.valueOf(aud);
                return staticUserMapper.selectById(userId);
            }else {
                return null;
            }

        } catch (Exception e) {
            log.error("analysis token fail", e);
            return null;
        }
    }
}