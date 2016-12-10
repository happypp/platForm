package com.platform.service;

import com.platform.dao.BaseDao;
import com.platform.entities.Subject;

import java.util.List;


public interface SubjectService extends BaseDao<Subject> {
	public List<Subject> getPageSubject(int pageIndex, int pageNum);
}
