package com.platform.service.Impl;

import com.platform.dao.impl.BaseDaoImpl;
import com.platform.entities.Subject;
import com.platform.service.SubjectService;
import org.springframework.stereotype.Service;

import java.util.List;



@Service("subjectService")
public class SubjectServiceImpl extends BaseDaoImpl<Subject> implements
		SubjectService {

	@Override
	public List<Subject> getPageSubject(int pageIndex, int pageNum) {
		String hql = "from Subject order by lasttime desc";
			List<Subject> list = this.getCurrentSession().createQuery(hql)//
								.setFirstResult((pageIndex-1)*pageNum)//
								.setMaxResults(pageNum)//
								.list();
		return list;
	}
}
