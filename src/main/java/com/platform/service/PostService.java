package com.platform.service;

import com.platform.dao.BaseDao;
import com.platform.entities.Post;

import java.util.List;


public interface PostService extends BaseDao<Post> {

    //查询最后回复三位用户回复的信息
    public List<Post> lReply(int sid,int m);

    //分页查询每个subject下的post
    public List<Post> getPagePost(int pageIndex,int pageNum,int sid);
}
