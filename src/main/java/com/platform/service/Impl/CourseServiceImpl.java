package com.platform.service.Impl;

import com.platform.dao.impl.BaseDaoImpl;
import com.platform.entities.Course;
import com.platform.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/11/2.
 */
@Service("courseService")
public class CourseServiceImpl extends BaseDaoImpl<Course> implements CourseService {

   @Override
   public List<Course> getPageCourse(Integer pageIndex, int pageNum, Integer courseId) {
        String hql = "from Course where lid = ?";
        List<Course> courses = this.getCurrentSession().createQuery(hql).setParameter(0,courseId)//
                .setFirstResult((pageIndex - 1) * pageNum)//
                .setMaxResults(pageNum)//
                .list();

        return courses;
   }

    @Override
    public List<Course> getPageCourse(Integer pageIndex, int pageNum, String key) {
        String hql = "from Course where title like ?";
        List<Course> courses =this.getCurrentSession().createQuery(hql).setParameter(0,"%"+key+"%")//
                .setFirstResult((pageIndex - 1) * pageNum)//
                .setMaxResults(pageNum)//
                .list();

        return courses;
    }

}
