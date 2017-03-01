package com.platform.csfservice.impl;

import com.platform.csfservice.UserServiceCxf;
import com.platform.dao.impl.BaseDaoImpl;
import com.platform.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pp on 2017/3/1.
 */
@Service("userServiceCxf")
public class UserServiceCxfImpl extends BaseDaoImpl<User> implements UserServiceCxf {

    @Override
    public User loginUser(String name, String password) {
        List<User> user = (List<User> ) this.find("from User t where t.name = ? and t.password = ?",new Object[]{"学生","4297f44b13955235245b2497399d7a93"});
        System.out.println(user);
        return user.get(0);
    }
}
