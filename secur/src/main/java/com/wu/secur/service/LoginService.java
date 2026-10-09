package com.wu.secur.service;

import com.wu.secur.domain.ResponseResult;
import com.wu.secur.domain.User;

public interface LoginService {
    ResponseResult login(User user);
}
