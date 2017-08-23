package com.platform.controller;

import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import com.platform.constant.Constant;
import com.platform.entities.Chapter;
import com.platform.entities.Course;
import com.platform.entities.User;
import com.platform.entities.Video;
import com.platform.service.ChapterService;
import com.platform.service.CourseService;
import com.platform.service.UserService;
import com.platform.service.VideoService;
import com.platform.util.Util;
import com.platform.vo.ChapterVideoVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/11/11.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private ChapterService chapterService;
    @Resource
    private VideoService videoService;
    @Resource
    private CourseService courseService;


    /**
     * 用户个人信息页面
     * @param userId
     * @return
     */
    @RequestMapping("/userInfoUI/{userId}")
    public ModelAndView userInfoUI(@PathVariable Integer userId){
        ModelAndView mv = new ModelAndView("userMsg");
        return mv;
    }

    /**
     * 用户登录的方法
     * @param email
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public @ResponseBody
    Integer login(@RequestParam(value = "email") String email,
                  @RequestParam(value = "password") String password, HttpSession session,HttpServletRequest request) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(Util.encryptMD5(password));
        User result = userService.login(user);
        if (result != null) {
            if(result.getVideoinfo() != null){
                int count = videoService.count("select count(*) from Video where videoname = ?",new Object[]{result.getVideoinfo()}).intValue();
                if(count == 0){
                    userService.executeHql("update User set videoinfo = null");
                    //重新获取user信息
                    result = userService.login(user);
                }
            }
            if(result.getState() == null){
                System.out.println("该账号"+email+"还没有激活不能够登录~!");
                return 2;
            }else {
                user = new User();
                user.setId(result.getId());
                user.setName(result.getName());
                user.setType(result.getType());
                user.setAvatar(Util.realAvatarUrl(result.getAvatar()));
                user.setVideoinfo(result.getVideoinfo());
                if(result.getVideoinfo() != null && !"".equals(result.getVideoinfo())){
                    List<Video> videos = videoService.find("select new Video(videoaddr,chapterid) from Video where videoname = ? ",new Object[]{result.getVideoinfo()});
                    if(!videos.isEmpty()){
                        Chapter chapter = chapterService.find("select new Chapter(chaptername,courseid) from Chapter where id = ? ", new Object[]{videos.get(0).getChapterid()}).get(0);
                        List<Course> courses = courseService.find("select new Course(title) from Course where id = ?",new Object[]{chapter.getCourseid()});
                        if(!courses.isEmpty()){
                            ChapterVideoVo chapterVideoVo = new ChapterVideoVo(chapter, videos.get(0),courses.get(0));
                            session.setAttribute("chapterVideoVo",chapterVideoVo);
                        }
                    }
                }
                session.setAttribute("user", user);
                System.out.println(user.getName() + "--->" + Util.getClientIp(request) + "---> 已登录该系统");
                return 1;
            }
        }else{
            return 0;
        }

    }

    /**
     * 用户注销功能
     */
    @RequestMapping("/logout")
    public @ResponseBody Integer logout(HttpSession session,HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        session.removeAttribute("user");
        System.out.println(user.getName() + "--->" + Util.getClientIp(request) + "---> 已退出系统~!");
        return 1;
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping("/regist")
    public String registUI(){
        return "regist";
    }

    @ResponseBody
    @RequestMapping("/userVideo")
    public void userVideoInfo(@RequestParam("uid") Integer uid,@RequestParam("videotitle") String videotitle,HttpSession session){
        userService.executeHql("update User set videoinfo = ? where id = ?", new Object[]{videotitle,uid});
        Video video = videoService.find("select new Video(videoaddr,chapterid) from Video where videoname = ? ",new Object[]{videotitle}).get(0);
        Chapter chapter = chapterService.find("select new Chapter(chaptername,courseid) from Chapter where id = ? ", new Object[]{video.getChapterid()}).get(0);
        Course course = courseService.find("select new Course(title) from Course where id = ?",new Object[]{chapter.getCourseid()}).get(0);
        User user = userService.find("from User where videoinfo = ?",new Object[]{videotitle}).get(0);
        userService.countermand(user);
        user.setAvatar(Util.realAvatarUrl(user.getAvatar()));
        session.setAttribute("user",user);
        ChapterVideoVo chapterVideoVo = new ChapterVideoVo(chapter,video,course);
        session.setAttribute("chapterVideoVo",chapterVideoVo);
    }

    /**
     * 用户邮箱验证
     * @param user
     * @param resp
     */
    @ResponseBody
    @RequestMapping(value = "/registVal/email",method = RequestMethod.POST)
    public void email(User user, HttpServletResponse resp) {
        String info = "";
        // 邮箱格式
        Pattern p = Pattern
                .compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
        Matcher m = p.matcher(user.getEmail());
        if (m.matches()) {
            List<User> users = userService.find("from User where email = ?",
                    new Object[] { user.getEmail() });
            if (users.isEmpty()) {
                info = "";
            } else {
                if(users.get(0).getState() == null){
                    userService.executeHql("delete from User where id = ?",new Object[]{users.get(0).getId()});
                    info = "";
                }else {
                    info = "邮箱已存在请重新填写";
                }
            }
        } else {
            info = "邮箱格式不正确";
        }
        resp.setCharacterEncoding("UTF-8"); // 输出流使用utf-8编码
        try {
            resp.getWriter().print(info);// 输出返回值
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户名验证
     * @param user
     * @param resp
     */
    @ResponseBody
    @RequestMapping(value = "/registVal/name",method = RequestMethod.POST)
    public void name(User user, HttpServletResponse resp) {
        String info = "";
        if (user.getName().length() < 16 && user.getName().length() > 1) {
            List<User> users = userService.find("from User where name = ?",
                    new Object[] { user.getName() });
            if (users.isEmpty()) {
                info = "";
            } else {
                info = "该昵称已被注册！";
            }
        } else {
            info = "昵称长度不符合要求(2~15)";
        }
        resp.setCharacterEncoding("UTF-8"); // 输出流使用utf-8编码
        try {
            resp.getWriter().print(info);// 输出返回值
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户密码验证
     * @param user
     * @param resp
     */
    @ResponseBody
    @RequestMapping(value = "/registVal/password", method = RequestMethod.POST)
    public void password(User user, HttpServletResponse resp) {
        String info = "";
        if (user.getPassword().length() < 11 && user.getPassword().length() > 4) {
            info = "";
        } else {
            info = "密码长度不正确(5~10)";
        }
        resp.setCharacterEncoding("UTF-8"); // 输出流使用utf-8编码
        try {
            resp.getWriter().print(info);// 输出返回值
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 用户重复密码验证
     * @param user
     * @param rpwd
     * @param resp
     */
    @ResponseBody
    @RequestMapping(value = "/registVal/rpwd", method = RequestMethod.POST)
    public void rpwd(User user,
                     @RequestParam(value = "rpwd", required = false) String rpwd,
                     HttpServletResponse resp) {
        String info = "";
        if (!user.getPassword().equals(rpwd.trim())) {
            info = "两次密码不相同";
        } else {
            info = "";
        }
        resp.setCharacterEncoding("UTF-8"); // 输出流使用utf-8编码
        try {
            resp.getWriter().print(info);// 输出返回值
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 账号激活码发送
     */
    @RequestMapping(value = "/sendEmail")
    public String sendEmail(User user,HttpSession session1,HttpServletRequest request){
        String email = user.getEmail();
        user.setPassword(Util.encryptMD5(user.getPassword()));
        userService.save(user);
        //TODO 路劲写死了
        Session session = MailUtils.createSession("smtp.163.com", "pp957655440@163.com", "pp969926449");
        String path = request.getServerName();
        if(path.equals("localhost")){
            path = request.getServerName() + ":8080";
        }
        Mail mail = new Mail("pp957655440@163.com", email, "账号激活码",
                "点击该激活码<a href=\"http://"+path+""+request.getContextPath()+"/user/activateUser\">"+ UUID.randomUUID().toString()+"</a>激活账号请鼠标右击打开~!半小时后失效~！");
        try {
            MailUtils.send(session, mail);
            session1.setAttribute("activateEmail",email);
            return "";
        } catch (Exception e) {
            session1.setAttribute("message","连接超时~！");
            return "message";
        }
    }

    /**
     * 用户账号激活
     */
    @RequestMapping(value = "/activateUser",method = RequestMethod.GET)
    public String activateUser(HttpSession session,HttpServletRequest request){
        Object activateEmail = session.getAttribute("activateEmail");
        session.removeAttribute("activateEmail");
        User u = null;
        if(activateEmail == null) {
            request.setAttribute("message","激活码已失效~!");
            return "message";
        }else{
            try {
                u = userService.find("from User where email = ?", new Object[]{activateEmail.toString()}).get(0);
            }catch (Exception e){
                request.setAttribute("message","激活码已失效~!");
                return "message";
            }
            userService.executeHql("update User set avatar = ?,state = ?,type = ?" +
                    " where id = ? ", new Object[]{"default.png",1, Constant.STUFENT,u.getId()});
            return "redirect:/index";
        }
    }
    /**
     * 昵称修改认证
     */
    @ResponseBody
    @RequestMapping("/editName")
    public void editUserName(Integer id,String name,HttpServletResponse resp){
        String info = "";
        User user = userService.get(id);
        if(name.equals(user.getName())){
            return;
        }
        List<User> users = userService.find("from User where name = ? and id not in(?)", new Object[]{name, id});
        if(users.isEmpty()){
            info = "";
        }else {
            info = "该昵称已存在，请重新填写~！";
        }
        resp.setCharacterEncoding("UTF-8"); // 输出流使用utf-8编码
        try {
            resp.getWriter().print(info);// 输出返回值
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断原始密码是否存在
     * @param uid
     * @param password
     * @param resp
     */
    @RequestMapping("/passWordExistVal")
    public void passWordExistVal(Integer uid,String password,HttpServletResponse resp){
        String info = "";
        User user = userService.find("select new User(password) from User where id = ?",new Object[]{uid}).get(0);
        if (!Util.encryptMD5(password).equals(user.getPassword())) {
            info = "您的原始密码不正确~请重新输入原始密码~！";
        }
        resp.setCharacterEncoding("UTF-8"); // 输出流使用utf-8编码
        try {
            resp.getWriter().print(info);// 输出返回值
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改密码
     * @param uid
     * @param password
     * @return
     */
    @RequestMapping("/editPwd")
    public @ResponseBody
        Integer editPwd(Integer uid,String password){
        userService.executeHql("update User set password = ? where id = ?",new Object[]{Util.encryptMD5(password),uid});
        return 1;
    }

    /**
     * 跳转---->forgetPwdUI
     * @return
     */
    @RequestMapping("/forgetPwdUI")
    public ModelAndView forgetPwdUI(){
        ModelAndView mv = new ModelAndView("forgetPwd");
        return mv;
    }

    /**
     * 找回密码邮箱认证
     * @return
     */
    @RequestMapping("/forget_email_val")
    public @ResponseBody
        Integer forget_email_val(@RequestParam String email){
        List<User> users = userService.find("from User where email = ?", new Object[]{email});
        if(!users.isEmpty()){
            return 1;
        }
        return 0;
    }

    /**
     *
     * 找回密码：发送邮箱到相对应用户
     */
    @RequestMapping("/emailToUser")
    public @ResponseBody
        Integer emailToUser(@RequestParam String email,HttpSession session1){
        session1.removeAttribute("code");
        String code = Util.getUUID();
        Session session = MailUtils.createSession("smtp.163.com", "pp957655440@163.com", "pp969926449");
        Mail mail = new Mail("pp957655440@163.com", email, "找回密码","该<span style='color:red;'>"+code+"</span>是重新设置密码的验证码~!");
        try {
            MailUtils.send(session, mail);
            session1.setAttribute("code",code);
            System.out.println("发送成功" + email +"---"+ session1.getAttribute("code"));
            return 0;  //发送成功
        } catch (Exception e) {
            return 1;  //连接超时
        }
    }

    /**
     * 找回密码：验证code是否正确
     * @return
     */
    @RequestMapping("/code_val")
    public @ResponseBody
        Integer code_val(@RequestParam String code,HttpSession session){
        if(code.equals(session.getAttribute("code"))){
            return 1;
        }
        return 0;
    }

    /**
     * 找回密码：重置用户密码
     * @param email
     * @param pwd
     * @return
     */
    @RequestMapping("/updateUserPwd")
    public @ResponseBody
        Integer updateUserPwd(@RequestParam String email,@RequestParam String pwd){
        Integer count = userService.executeHql("update User set password = ? where email = ?", new Object[]{Util.encryptMD5(pwd), email});
        if(count >= 1){
            return 1;
        }
        return 0;
    }

}
