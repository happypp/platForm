package com.platform.controller;

import com.platform.constant.Constant;
import com.platform.entities.*;
import com.platform.service.*;
import com.platform.task.ArticlePipeline;
import com.platform.task.ArticleSpider;
import com.platform.util.Page;
import com.platform.vo.ChapterVideoVo;
import com.platform.vo.CourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/2.
 */

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private LeveCourseService leveCourseService;
    @Autowired
    private CourseTypeService courseTypeService;

    @RequestMapping("/chapterSave")
    public String chapterSave(){
        ArticleSpider articleSpider = new ArticleSpider();
            Spider.create(articleSpider).addPipeline(new ArticlePipeline())
                    .addUrl("http://www.imooc.com/learn/39").thread(1).run();

        for(Video video : articleSpider.getVideoLists()){
            videoService.save(video);
        }

        for (Chapter chapter :articleSpider.getChapterLists()){
            chapterService.save(chapter);
        }
        return "";
    }

    @RequestMapping("/save")
    public String save(){
        ArticleSpider articleSpider = new ArticleSpider();
        Spider.create(articleSpider).addPipeline(new ArticlePipeline())
                .addUrl("http://www.imooc.com/course/list?c=ios&is_easy=1").thread(1).run();
        System.out.println(articleSpider.getList());

        for (Course course : articleSpider.getList()){
             courseService.save(course);
        }
        return "";
    }

    @RequestMapping("/courseIndex")
    public ModelAndView courseIndex(){
        ModelAndView mv = new ModelAndView("courseIndex");
        List<CourseVo> courseVos = getCourseVo();
        mv.addObject("courseVos",courseVos);
        return mv;
    }

    /**
     * 显示课程相关信息
     * @return
     */
    @RequestMapping("/courseMsg/{courseId}/{pageIndex}")
    public ModelAndView courseMsg(@PathVariable Integer courseId,@PathVariable Integer pageIndex){
        //查询一级课程的信息
        List<LeveCourse> leveCourses = leveCourseService.find("select new LeveCourse(coursename,description) from LeveCourse where id = ?", new Object[]{courseId});
        ModelAndView mv = new ModelAndView("courseMsg");
        if(!leveCourses.isEmpty()){
            mv.addObject("leveCourse",leveCourses.get(0));
        }
        // 获取二级课程信息
        List<Course> courses = courseService.getPageCourse(pageIndex,Constant.LIST_COURSE,courseId);
        if (!courses.isEmpty()) {
            mv.addObject("courses",courses);
        }
        Long count = courseService.count("select count(id) from Course where lid = ?", new Object[]{courseId});
        Page page = new Page(pageIndex,count.intValue());
        mv.addObject("page",page);

        mv.addObject("urlSubfix","/course/courseMsg/"+courseId);
        //获取二级菜单数据
        List<CourseVo> courseVos = getCourseVo();
        mv.addObject("courseVos",courseVos);
        return mv;
    }

    /**
     * 获取二级菜单数据
     * @return
     */
    public List<CourseVo> getCourseVo(){
        //获取二级菜单数据
        List<CourseType> courseTypes = courseTypeService.find("from CourseType");
        List<CourseVo> courseVos = new ArrayList<CourseVo>();
        for (CourseType courseType : courseTypes){
            List<LeveCourse> leveCourses = leveCourseService.find("select new LeveCourse(id,coursename) from LeveCourse where typeid = ?",new Object[]{courseType.getId()});
            CourseVo courseVo = new CourseVo(leveCourses,courseType.getName());
            courseVos.add(courseVo);
        }
        return courseVos;
    }


    /**
     * 跳转到课程视频播放页面
     */
    @RequestMapping("/courseVideo/{courseId}")
    public ModelAndView courseVideo(@PathVariable Integer courseId){
        ModelAndView mv = new ModelAndView("courseVideo");
        courseService.executeHql("update Course set playnum=playnum+1 where id = ?",new Object[]{courseId});
        List<Chapter> chapters = chapterService.find("from Chapter where courseid = ?", new Object[]{courseId});
        List<Course> courses = courseService.find("select new Course(title,needinfo,studyinfo,courseware) from Course where id = ?", new Object[]{courseId});
        if(!courses.isEmpty()){
            mv.addObject("course",courses.get(0));
        }
        if(chapters != null){
            List<ChapterVideoVo> chapterVideoVos = new ArrayList<ChapterVideoVo>();
            for(Chapter chapter : chapters){
                List<Video> videos = videoService.find("from Video where chapterid = ?", new Object[]{chapter.getId()});
                chapterVideoVos.add(new ChapterVideoVo(chapter,videos));
            }
            mv.addObject("chapterVideoVos",chapterVideoVos);
        }
        return mv;
    }
}
