package com.platform.service;

import com.platform.dao.BaseDao;
import com.platform.entities.Course;

import java.util.List;

/**
 * Created by Administrator on 2015/11/2.
 */
public interface CourseService extends BaseDao<Course> {
    //课程信息分页
    List<Course> getPageCourse(Integer pageIndex, int pageNum, Integer courseId);
    //查询课程分页
    List<Course> getPageCourse(Integer pageIndex, int pageNum,String key);
}
