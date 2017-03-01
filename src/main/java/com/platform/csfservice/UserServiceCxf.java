package com.platform.csfservice;

import com.platform.dao.BaseDao;
import com.platform.entities.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by pp on 2017/3/1.
 */
@WebService
public interface UserServiceCxf extends BaseDao<User> {
    @WebMethod
    public User loginUser(@WebParam(name="name") String name, @WebParam(name="password") String password);
}
