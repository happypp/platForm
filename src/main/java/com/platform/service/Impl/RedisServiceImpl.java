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

    /**
     * 获取redis数据
     * @param email
     * @return
     */
    @Override
    public User getUserRedis(String email) {
        ValueOperations<String,User> valueOperations = redisTemplate.opsForValue();
        User user = valueOperations.get(email);
        return user;
    }

    /**
     * 更新redis数据
     * @param user
     */
    @Override
    public void setUserRedis(User user) {
        ValueOperations<String,User> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(user.getEmail(),user);
    }

    /**
     * 删除redis中的数据
     * @param email
     */
    @Override
    public void delUserRedis(String email) {
        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
        User user = valueOperations.get(email);
        if(user != null){
            redisTemplate.delete(email);
        }
    }
}
