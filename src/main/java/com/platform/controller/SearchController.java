package com.platform.controller;

import com.platform.constant.Constant;
import com.platform.entities.Course;
import com.platform.service.CourseService;
import com.platform.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by lenovo on 2015/12/26.
 */
@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private CourseService courseService;



    @RequestMapping("/index/{courseText}/{pageIndex}")
    public ModelAndView searchUI(@PathVariable String courseText,@PathVariable Integer pageIndex) throws UnsupportedEncodingException {
        ModelAndView mv = new ModelAndView("searchIndex");
        String course = java.net.URLDecoder.decode(courseText,"UTF-8");
        List<Course> courseList = courseService.getPageCourse(pageIndex, Constant.LIST_COURSE, course);
        Long count = courseService.count("select count(id) from Course where title like ?",new Object[]{"%"+courseText+"%"});
        Page page = new Page(pageIndex,count.intValue());
        mv.addObject("page",page);
        mv.addObject("urlSubfix","/search/index/"+course);
        mv.addObject("courseList",courseList);
        mv.addObject("courseText",course.toUpperCase());
        return mv;
    }
}
