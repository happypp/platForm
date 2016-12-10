package com.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.platform.constant.Constant;
import com.platform.entities.*;
import com.platform.service.*;
import com.platform.util.Util;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.rs.PutPolicy;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/5.
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private LeveCourseService leveCourseService;
    @Autowired
    private UserService userService;

    @RequestMapping("/uploadUI")
    public ModelAndView uploadUI(){
        ModelAndView mv = new ModelAndView("uploadVideo");
        //获取二级标题的所有id、coursename
        List<LeveCourse> leveCourses = leveCourseService.find("select new LeveCourse(id,coursename) from LeveCourse");
        mv.addObject("leveCourses",leveCourses);
        return mv;
    }

    /**
     * 上传视频
     * @param title 课程标题
     * @param tip   课程小技巧
     * @param needinfo 课程需学
     * @param studyinfo 课程学会
     * @param chaptername 章节名称
     * @param videoname 视频名称
     * @param courseimg 课程图片
     * @param videoaddr 视频地址
     * @param lid 二级课程id
     */
    @RequestMapping(value = "/video",method = RequestMethod.POST)
    public String uploadVideo(@RequestParam(value = "title") String title,
                            @RequestParam(value = "tip",required = false) String tip,
                            @RequestParam(value = "needinfo",required = false) String needinfo,
                            @RequestParam(value = "studyinfo",required = false) String studyinfo,
                            @RequestParam(value = "chaptername") String chaptername,
                            @RequestParam(value = "videoname") String videoname,
                            @RequestParam(value = "courseimg",required = false) MultipartFile courseimg,
//                            @RequestParam(value = "videoaddr") MultipartFile videoaddr,
                              @RequestParam(value = "videoaddr") String videoaddr,
                            @RequestParam(value = "lid",required = false) Integer lid,@RequestParam(value = "courseware") String courseware) throws IOException {
        String imageUUID = Util.getUUID();
        //String videoUUID = Util.getUUID();
        if(courseimg != null){
            uploadImgAndVideo(courseimg.getInputStream(), courseimg,imageUUID);
        }
        //uploadImgAndVideo(videoaddr.getInputStream(),videoaddr,videoUUID);
        String imageUrl = null;
        if(courseimg != null){
            imageUrl = imageUUID+"."+courseimg.getOriginalFilename().substring(courseimg.getOriginalFilename().lastIndexOf(".")+1,courseimg.getOriginalFilename().length());
        }
        Course course = getCourseInfo(imageUrl,title,tip,needinfo,studyinfo,lid,courseware);
        courseService.countermand(course);
        Chapter chapter = getChapterInfo(chaptername,course);
        chapterService.countermand(chapter);
        //String videoStr = videoUUID +"."+videoaddr.getOriginalFilename().substring(videoaddr.getOriginalFilename().lastIndexOf(".")+1,videoaddr.getOriginalFilename().length());
        //Video video = new Video(videoname,Constant.VIDEO_URL_QINNIU+videoStr,chapter.getId());
        Video video = new Video(videoname, videoaddr, chapter.getId());
        videoService.save(video);
        return "redirect:/upload/uploadUI";
    }

    //获取相关章节信息
    private Chapter getChapterInfo(String chaptername,Course course){
        List<Chapter> chapters = chapterService.find("select new Chapter(id,chaptername) from Chapter where courseid = ?", new Object[]{course.getId()});
        Chapter chapter = null;
        if(chapters.isEmpty()){
            chapter = new Chapter(chaptername,course.getId());
            chapterService.save(chapter);
            chapter = chapterService.find("select new Chapter(id) from Chapter where chaptername = ?", new Object[]{chapter.getChaptername()}).get(0);
        }else{
            List<String> strings = new ArrayList<String>();
            for(Chapter chapter1 : chapters){
                strings.add(chapter1.getChaptername());
            }
            Boolean aBoolean = strings.contains(chaptername);
            if(aBoolean){
                chapter = chapterService.find("select new Chapter(id) from Chapter where chaptername = ?", new Object[]{chaptername}).get(0);
            }else{
                chapter = new Chapter(chaptername,course.getId());
                chapterService.save(chapter);
                chapter = chapterService.find("select new Chapter(id) from Chapter where chaptername = ?", new Object[]{chapter.getChaptername()}).get(0);
            }
        }
        return chapter;
    }


    //获取课程信息
    private Course getCourseInfo(String courseimg,String title,String tip,String needinfo,String studyinfo,Integer lid,String courseware){
        List<Course> courses = courseService.find("select new Course(id) from Course where title = ?",new Object[]{title});
        Course course = null;
        if(courses.isEmpty()){
            course = new Course(Constant.VIDEO_URL_QINNIU+courseimg,title,tip,0,needinfo,studyinfo,lid,courseware);
            courseService.save(course);
            course = courseService.find("select new Course(id) from Course where title = ?", new Object[]{title}).get(0);
        }else{
            if(!Util.isNull(courseware)){
                courseService.executeHql("update Course set courseware = ? where id = ?",new Object[]{courseware,courses.get(0).getId()});
            }
            course = courseService.find("select new Course(id) from Course where id = ?",new Object[]{courses.get(0).getId()}).get(0);
        }
        return course;
    }

    //上传图片和视频
    private void uploadImgAndVideo(InputStream in, MultipartFile file,String UUID){
        Config.ACCESS_KEY = "AJ1DUPV-BcrX96n9FhK2f9xt5UVJfWa6mjHnMqkw";
        Config.SECRET_KEY = "v2eYvPYHFVvBlIb8076ujykBqe-cTkeiN7bzNbHb";
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        // 请确保该bucket已经存在
        String bucketName = "pp-platform";
        PutPolicy putPolicy = new PutPolicy(bucketName);
        String uptoken = null;
        try {
            uptoken = putPolicy.token(mac);
            //putPolicy.persistentOps = "avthumb/mp4";
            putPolicy.marshal();
        } catch (AuthException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        PutExtra extra = new PutExtra();
        String key = UUID +"."+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1,file.getOriginalFilename().length());
        IoApi.Put(uptoken, key, in, extra);
    }

    @ResponseBody
    @RequestMapping("/course/{title}")
    public void course(@PathVariable String title,HttpServletResponse resp){
        List<Course> courses = courseService.find("from Course where title = ?", new Object[]{title});
        resp.setCharacterEncoding("UTF-8"); // 输出流使用utf-8编码
        if(!courses.isEmpty()){
            String s = JSONObject.toJSONString(courses.get(0));
            try {
                resp.getWriter().write(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                resp.getWriter().write("1");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/editUserInfo",method = RequestMethod.POST)
    public ModelAndView editUserInfo(Integer uid,String name,MultipartFile avatar,HttpSession session) throws IOException {
        ModelAndView mv = new ModelAndView("userMsg");
        String avatarUUID = Util.getUUID();
        String avatarUrl = avatarUUID + "." + avatar.getOriginalFilename().substring(avatar.getOriginalFilename().lastIndexOf(".")+1,avatar.getOriginalFilename().length());
        userService.executeHql("update User set name = ?,avatar = ? where id =?",new Object[]{name,avatarUrl,uid});
        uploadAvatarImg(avatar.getInputStream(), avatarUrl);
        User user = userService.get(uid);
        session.setAttribute("user",user);
        return mv;
    }

    private void uploadAvatarImg(InputStream inputStream, String avatarUrl) {
        Config.ACCESS_KEY = "AJ1DUPV-BcrX96n9FhK2f9xt5UVJfWa6mjHnMqkw";
        Config.SECRET_KEY = "v2eYvPYHFVvBlIb8076ujykBqe-cTkeiN7bzNbHb";
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        // 请确保该bucket已经存在
        String bucketName = "pp-xx";
        PutPolicy putPolicy = new PutPolicy(bucketName);
        String uptoken = null;
        try {
            uptoken = putPolicy.token(mac);
            //putPolicy.persistentOps = "avthumb/mp4";
            putPolicy.marshal();
        } catch (AuthException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        PutExtra extra = new PutExtra();
        String key = "platform_assets/images/avatar/"+ avatarUrl;
        IoApi.Put(uptoken, key, inputStream, extra);
    }
}
