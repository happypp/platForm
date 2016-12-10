package com.platform.service;

import com.platform.dao.BaseDao;
import com.platform.entities.User;

/**
 * Created by Administrator on 2015/11/11.
 */
public interface UserService extends BaseDao<User> {
    public User login(User user);
}
