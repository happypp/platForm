package com.platform.service.Impl;

import com.platform.dao.impl.BaseDaoImpl;
import com.platform.entities.User;
import com.platform.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Administrator on 2015/11/11.
 */
@Service("userService")
public class UserServiceImpl extends BaseDaoImpl<User> implements
        UserService {

        @Override
        public User login(User user) {
                String hql = "from User where email=? and password=?";
                List<User> list = this.find(hql, new Object[]{user.getEmail(),user.getPassword()});
                return list.size() == 0 ? null : list.get(0);
        }
}
