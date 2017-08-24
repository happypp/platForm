package com.platform.service;

import com.platform.entities.User;

/**
 * Created by pp on 2017/8/23.
 */
public interface RedisService {

    public User getUserRedis(String email);

    public void setUserRedis(User user);
}
