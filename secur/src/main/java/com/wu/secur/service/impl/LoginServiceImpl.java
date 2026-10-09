package com.wu.secur.service.impl;

import com.wu.secur.domain.LoginUser;
import com.wu.secur.domain.ResponseResult;
import com.wu.secur.domain.User;
import com.wu.secur.service.LoginService;
import com.wu.secur.utils.JwtUtil;
import com.wu.secur.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult login(User user) {
        //AuthenticatonManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate=authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出对应的提示
        if(Objects.isNull(authenticationToken)){
            throw new RuntimeException("登录失败");
        }
        //如果认证通过了，使用userId生成一个jwt
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        //把完整的用户信息存入redis,userid作为key
        redisCache.setCacheObject("login:"+userId,loginUser);
        return new ResponseResult(200,"登录成功",map);



    }
}
