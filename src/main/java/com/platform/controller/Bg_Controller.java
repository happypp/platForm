package com.platform.controller;

import com.platform.constant.Constant;
import com.platform.entities.*;
import com.platform.service.*;
import com.platform.util.Util;
import com.platform.vo.CourseVo;
import com.platform.vo.LcourseTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/1/3.
 */
@Controller
public class Bg_Controller {

    @Autowired
    private CourseTypeService courseTypeService;
    @Autowired
    private LeveCourseService leveCourseService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private PostService postService;

    /**
     * 管理员登录
     * @return
     */
    @RequestMapping("/bg_login")
    public ModelAndView bg_login(){
        ModelAndView mv = new ModelAndView("bg_site/bg_login");
        return mv;
    }

    /**
     * 管理员注销
     * @param session
     * @return
     */
    @RequestMapping("/bg_logout")
    public String bg_logout(HttpSession session){
        session.removeAttribute("admin");
        return "redirect:/bg_login";
    }

    /**
     * 后台主界面
     * @return
     */
    @RequestMapping("/bg_index")
    public ModelAndView bg_index(){
        ModelAndView mv = new ModelAndView("bg_site/bg_index");
//        List<CourseType> courseTypes = courseTypeService.find("from CourseType");
        List<CourseVo> courseVos = getCourseVo();
        mv.addObject("courseVos",courseVos);
        //获取一级课程对应的二级课（1:1）
        List<LcourseTypeVo> lcourseTypeVos = getLcourseTypeVo();
        mv.addObject("lcourseTypeVos",lcourseTypeVos);
        return mv;
    }

    /**
     * 获取一级课程对应的二级课（1:1）
     * @return lcourseTypeVos
     */
    private List<LcourseTypeVo> getLcourseTypeVo() {
        List<LcourseTypeVo> lcourseTypeVos = new ArrayList<LcourseTypeVo>();
        LcourseTypeVo lcourseTypeVo = null;
        List<CourseType> courseTypeList = courseTypeService.find("from CourseType");
        for(CourseType courseType : courseTypeList){
            List<LeveCourse> leveCourses = leveCourseService.find("from LeveCourse where typeId = ?", new Object[]{courseType.getId()});
            if(leveCourses.isEmpty()){
                lcourseTypeVo = new LcourseTypeVo(courseType.getId(),null,courseType.getName(),null);
                lcourseTypeVos.add(lcourseTypeVo);
            }else{
                for(LeveCourse leveCourse : leveCourses){
                    lcourseTypeVo = new LcourseTypeVo(courseType.getId(),leveCourse.getId(),courseType.getName(),leveCourse.getCoursename());
                    lcourseTypeVos.add(lcourseTypeVo);
                }
            }
        }
        return lcourseTypeVos;
    }

    /**
     * 后台视频管理界面
     * @param id 课程ID
     * @return
     */
    @RequestMapping("/bg_courseIndex/{id}")
    public ModelAndView bg_courseIndex(@PathVariable Integer id){

        ModelAndView mv = new ModelAndView("bg_site/bg_courseIndex");
        mv.addObject("id",id);
        List<LeveCourse> courseName = leveCourseService.find("select coursename from LeveCourse where id = ?", new Object[]{id});
        //TODO 有问题（可以试试判断courseName不为空）
        if(!courseName.isEmpty()){
            mv.addObject("courseName",courseName.get(0));
        }
        List<Course> courses = courseService.find("select new Course(id,title,playnum) from Course where lid = ?", new Object[]{id});
        mv.addObject("courses",courses);
        List<CourseVo> courseVos = getCourseVo();
        mv.addObject("courseVos",courseVos);
        return mv;
    }

    /**
     * 删除相关课程
     * @param lid
     * @param cid
     */
    @RequestMapping("/bg_courseDeleteById/{lid}/{cid}")
    public @ResponseBody
        Integer bg_courseDeleteById(@PathVariable Integer lid,@PathVariable Integer cid){
        courseService.executeHql("delete from Course where id = ?",new Object[]{cid});
        List<Chapter> chapters = chapterService.find("select new Chapter(id) from Chapter where courseid = ?", new Object[]{cid});
        if(!chapters.isEmpty()){
            for(Chapter chapter : chapters){
                videoService.executeHql("delete Video where chapterid = ?",new Object[]{chapter.getId()});
            }
        }
        chapterService.executeHql("delete from Chapter where courseid = ?",new Object[]{cid});
        int count = courseService.count("select count(*) from Course where lid = ?", new Object[]{lid}).intValue();
        if(count == 0){
            leveCourseService.executeHql("delete from LeveCourse where id = ?",new Object[]{lid});
        }
        return 1;
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
            CourseVo courseVo = new CourseVo(courseType.getId(),leveCourses,courseType.getName());
            courseVos.add(courseVo);
        }
        return courseVos;
    }

    /**
     * 验证管理员密码是否正确
     * @param user
     * @return
     */
    @RequestMapping("/valPassWord")
    public @ResponseBody
        Integer valPassWord(User user,HttpSession session){
//        System.out.println(user);
        user.setPassword(Util.encryptMD5(user.getPassword()));
        User u1 = userService.login(user);
        if(u1 == null){
            return 0;
        }
        session.setAttribute("admin",u1);
        return 1;
    }

    /**
     * 添加一级课程、二级课程相关信息
     * @param name
     * @param coursename
     * @param description
     * @return
     */
    @RequestMapping("/saveCoursename")
    public @ResponseBody
        Integer saveCourse(@RequestParam String name,@RequestParam String coursename,
                           @RequestParam String description){
        Integer id = null;
        LeveCourse leveCourse = null;
        CourseType courseType = null;
        try {
           id = Integer.parseInt(name);
        }catch (Exception e){
            id = null;
        }
        if(id != null){
            CourseType courseType1 = courseTypeService.getById(id);
            if(courseType1 == null){
                courseType1 = new CourseType(id.toString());
                courseTypeService.save(courseType1);
                courseType1 = courseTypeService.find("from CourseType where name = ?",new Object[]{id.toString()}).get(0);
            }
            leveCourse = new LeveCourse(coursename,description,courseType1.getId());
//            leveCourseService.executeHql("insert into Levecourse(coursename,description,tyepId) values(?,?,?)",
//                    new Object[]{coursename,description,id});
            leveCourseService.save(leveCourse);
            return 1;
        }else {
            courseType = new CourseType(name);
//            courseTypeService.executeHql("insert into CourseType(name) values(?)",new Object[]{name});
            courseTypeService.save(courseType);
            courseType = courseTypeService.find("from CourseType where name = ?", new Object[]{name}).get(0);
            leveCourse = new LeveCourse(coursename,description,courseType.getId());
//            leveCourseService.executeHql("insert into Levecourse(coursename,description,tyepId) values(?,?,?)",
//                    new Object[]{coursename,description,courseType.getId()});
            leveCourseService.save(leveCourse);
            return 1;
        }
    }

    @RequestMapping("/bg_T_LcourseDeleteById")
    public @ResponseBody
        Integer bg_T_LcourseDeleteById(@RequestParam Integer lid,@RequestParam Integer tid){
        if(lid == null){
            courseTypeService.executeHql("delete from CourseType where id = ?",new Object[]{tid});
        }else{
            List<Course> courses = courseService.find("from Course where lid = ?",new Object[]{lid});
            if(!courses.isEmpty()){
                for(Course course : courses){
                    List<Chapter> chapters = chapterService.find("from Chapter where courseid = ?", new Object[]{course.getId()});
                    if(!chapters.isEmpty()){
                        for(Chapter chapter : chapters){
                            List<Video> videos = videoService.find("from Video where chapterid = ?", new Object[]{chapter.getId()});
                            if(!videos.isEmpty()){
                                videoService.executeHql("delete from Video where chapterid = ?",new Object[]{chapter.getId()});
                            }
                        }
                        chapterService.executeHql("delete from Chapter where courseid = ?",new Object[]{course.getId()});
                    }
                }
                courseService.executeHql("delete from Course where lid = ?",new Object[]{lid});
            }
            leveCourseService.executeHql("delete from LeveCourse where id = ?",new Object[]{lid});
            int count = leveCourseService.count("select count(*) from LeveCourse where typeId = ?",new Object[]{tid}).intValue();
            if(count == 0){
                courseTypeService.executeHql("delete from CourseType where id = ?",new Object[]{tid});
            }
        }
        return 1;
    }

    /**
     * 激活用户--->教师
     * @return
     */
    @RequestMapping("/updateUserType")
    public @ResponseBody
        Integer updateUserType(@RequestParam String email){
        List<User> users = userService.find("from User where email = ?", new Object[]{email});
        if(!users.isEmpty()){
            if("教师".equals(users.get(0).getType())){
                return 1;
            }else{
                userService.executeHql("update User set type = ? where id = ?",new Object[]{Constant.TEACHER,users.get(0).getId()});
                return 0;
            }
        }else{
            return 2;
        }
    }

    /**
     * 激活 教师 --> 普通用户
     * @param email
     * @return
     */
    @RequestMapping("/updateTeacherType")
    public @ResponseBody
        Integer updateTeacherType(@RequestParam String email){
        List<User> users = userService.find("from User where email = ?", new Object[]{email});
        if(!users.isEmpty()){
            if("学生".equals(users.get(0).getType())){
                return 1;
            }else{
                userService.executeHql("update User set type = ? where id = ?",new Object[]{Constant.STUFENT,users.get(0).getId()});
                return 0;
            }
        }else{
            return 2;
        }
    }

    /**
     * 管理员删除主题
     * @param sid
     * @return
     */
    @RequestMapping("/bg_deleteSub")
    public @ResponseBody
        Integer deleteSub(@RequestParam Integer sid){
        if(sid != null){
            subjectService.executeHql("delete Subject where id = ?",new Object[]{sid});
            postService.executeHql("delete Post where sid = ?",new Object[]{sid});
            return 1;
        }else{
            return 0;
        }

    }

    /**
     * 管理员删除Post
     * @param pid
     * @return
     */
    @RequestMapping("/bg_delete_post")
    public @ResponseBody
        Integer deletePost(@RequestParam Integer pid){
        if(pid != null){
            Post p = postService.find("from Post where id = ?", new Object[]{pid}).get(0);
            postService.executeHql("delete Post where pid = ?", new Object[]{p.getId()});
            postService.executeHql("delete Post where id = ?",new Object[]{pid});
            return 1;
        }else{
            return 0;
        }
    }

}
