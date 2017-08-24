package com.platform.service.Impl;

import com.platform.entities.User;
import com.platform.service.CourseService;
import com.platform.service.RedisService;
import org.hibernate.SessionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by pp on 2017/8/23.
 */
@Service("redisService")
public class RedisServiceImpl implements RedisService{

    @Resource
    private RedisTemplate<String,User> redisTemplate;


    @Override
    public User getUserRedis(String email) {
        ValueOperations<String,User> valueOperations = redisTemplate.opsForValue();
        User user = valueOperations.get(email);
        return user;
    }

    @Override
    public void setUserRedis(User user) {
        ValueOperations<String,User> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(user.getEmail(),user);
    }
}
