package com.platform.service.Impl;

import com.platform.dao.impl.BaseDaoImpl;
import com.platform.entities.Post;
import com.platform.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("postService")
public class PostServiceImpl extends BaseDaoImpl<Post> implements
		PostService {

	@Override
	public List<Post> lReply(int sid,int m) {
		String hql = "select new Post(id,sid,uid,max(time)) from Post where sid=? group by id,sid,uid order by max(time) desc";
		List<Post> list = (List<Post>) this.getCurrentSession()
				.createQuery(hql).setParameter(0, sid).setFirstResult(0).setMaxResults(m).list();
		return list;
	}

	@Override
	public List<Post> getPagePost(int pageIndex, int pageNum, int sid) {

		String hql = "from Post where sid = ? and pid is null";
		List<Post> posts = this.getCurrentSession().createQuery(hql).setParameter(0,sid)//
				.setFirstResult((pageIndex-1)*pageNum)//
				.setMaxResults(pageNum)//
				.list();
		return posts;
	}
}
