package com.platform.controller;

import com.platform.constant.Constant;
import com.platform.entities.Post;
import com.platform.entities.Subject;
import com.platform.entities.User;
import com.platform.service.PostService;
import com.platform.service.SubjectService;
import com.platform.service.UserService;
import com.platform.util.Page;
import com.platform.util.Util;
import com.platform.vo.SubjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/10.
 */
@Controller
@RequestMapping("/discuss")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @RequestMapping("/subjectIndex/{pageIndex}")
    public ModelAndView index(@PathVariable Integer pageIndex){
        ModelAndView mv = new ModelAndView("subject");
        //获取subject总数
        Long count = subjectService.count("select count(*) from Subject");
        Page page = new Page(pageIndex,count.intValue());
        mv.addObject("page",page);
        mv.addObject("urlSubfix","/discuss/subjectIndex");
        //查询分页的subject数据
        List<Subject> subjects = subjectService.getPageSubject(pageIndex, Constant.LIST_ITEM_NUM);
        List<SubjectVo> svos = new ArrayList<SubjectVo>();
        for(Subject s : subjects) {
            svos.add(getSVO(s));
        }
        mv.addObject("svos", svos);
        return mv;
    }

    private SubjectVo getSVO(Subject subject) {
        int sid = subject.getId();
        User user = userService.get(subject.getUid());
        SubjectVo.UserPost author = SubjectVo.newUserPost(user, subject.getTime());
        List<SubjectVo.UserPost> users = new ArrayList<SubjectVo.UserPost>();
        for(Post p : postService.lReply(sid, 3)) {
            User u = userService.find("from User where id = ?", new Object[]{p.getUid()}).get(0);
            users.add(SubjectVo.newUserPost(u, p));
        }
        int reply = postService.count(String.format("select count(*) from Post where sid=%s", sid)).intValue();
        String lastReply = Util.getSimpleTimeStr(subject.getLasttime());
        return new SubjectVo(sid, subject.getTitle(), author, users, reply,
                subject.getVisits(), lastReply);
    }

    /**
     * 保存主题
     */
    @RequestMapping("/saveSubject")
    public @ResponseBody
        Integer save(@RequestBody Subject subject){
        Timestamp time = Timestamp.valueOf(Util.nowTime());
        subject.setTime(time);
        subject.setLasttime(time);
        subject.setVisits(0);
        subjectService.save(subject);
        return 1;
    }


}
