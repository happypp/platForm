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
import com.platform.vo.PostVo;
import com.platform.vo.RPostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/17.
 */
@Controller
@RequestMapping("/subject")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private UserService userService;

    @RequestMapping("/post/{id}/{pageIndex}/{backPageIndex}")
    @ResponseBody
    public ModelAndView showPost(@PathVariable Integer id,@PathVariable Integer pageIndex,
                                 @PathVariable Integer backPageIndex,
                                 @RequestParam(value = "pid",required = false) Integer pid,
                                 @RequestParam(value = "reply_value",required = false) String reply_value,
                                 @RequestParam(value = "uid",required = false) Integer uid){
        if(pid != null && reply_value != null && uid != null){
            Post post = new Post();
            post.setSid(id);
            post.setUid(uid);
            post.setPid(pid);
            post.setContent(reply_value);
            Timestamp time = Timestamp.valueOf(Util.nowTime());
            post.setTime(time);
            postService.save(post);
            subjectService.executeHql("update Subject set lasttime = ? where id=?",
                    new Object[]{time, post.getSid()});
        }

        Subject subject = subjectService.getById(id);
        //获取发帖者的相关信息
        User author = userService.get(subject.getUid());
        userService.countermand(author);
        if(author != null){
            author.setAvatar(Util.realAvatarUrl(author.getAvatar()));
        }

        ModelAndView mv = new ModelAndView("post");

        //获取回复该主题的信息以及发表回复的User(分页查询)
        List<Post> posts = postService.getPagePost(pageIndex, Constant.LIST_ITEM_NUM,id);
        Long count = postService.count("select count(id) from Post where sid = ? and pid is null",new Object[]{id});
        Page page = new Page(pageIndex,count.intValue());
        mv.addObject("page",page);

        List<PostVo> postVos = new ArrayList<PostVo>();
        PostVo postVo = null;
        for(Post post : posts){
            List<RPostVo> rPostVos = new ArrayList<RPostVo>();
            User uuser = userService.find("from User where id = ?", new Object[]{post.getUid()}).get(0);
            String userAvatar = "";
            if(uuser!=null){
                userService.countermand(uuser);
                if(uuser.getAvatar().contains("/platform_assets/images/avatar/")){
                    userAvatar = uuser.getAvatar();
                }else {
                    userAvatar = Util.realAvatarUrl(uuser.getAvatar());
                }
                List<Post> posts1 = postService.find("from Post where pid = ? order by time desc", new Object[]{post.getId()});
                if(!posts1.isEmpty()){
                    for(Post post1 : posts1){
                        RPostVo rPostVo = null;
                        User user = userService.find("from User where id = ?", new Object[]{post1.getUid()}).get(0);
                        userService.countermand(user);
                        String time = Util.getSimpleTimeStr(post1.getTime());
                        if(!user.getAvatar().contains("/platform_assets/images/avatar/")){
                            user.setAvatar(Util.realAvatarUrl(user.getAvatar()));
                        }
                        rPostVo = new RPostVo(post1,user,time);
                        rPostVos.add(rPostVo);
                    }                                                            //获取每个Rpost的user和time
                    postVo = new PostVo(uuser,post,userAvatar,rPostVos);
                }else{
                    postVo = new PostVo(uuser,post,userAvatar,null);
                 }
                postVos.add(postVo);
            }
        }
//        System.out.println(postVos.size());
        //查询最后一个回复的用户
        List<Post> list = postService.lReply(id, 1);
        if (list != null && list.size() != 0) {
            User user = userService.getById(list.get(0).getUid());
            PostVo pov = new PostVo(user, list.get(0),Util.realAvatarUrl(user.getAvatar()));
            mv.addObject("lastPost", pov);
        }

        //回复帖子的数量
        Long num = postService.count("select count(*) from Post where sid = ?", new Object[]{subject.getId()});
        if(num.intValue() > 0){
            mv.addObject("num",num);
        }
        //浏览次数
        subjectService.executeHql("update Subject set visits = visits + 1 where id = ?",new Object[]{id});

        mv.addObject("backPageIndex", backPageIndex);
        mv.addObject("createTime",Util.getSimpleTimeStr(subject.getTime()));
        mv.addObject("subject",subject);
        mv.addObject("author",author);
        mv.addObject("postVos",postVos);
        mv.addObject("urlSubfix","/subject/post/"+id);


        return mv;
    }


    /**
     * 保存用户发布的POST
     */
    @RequestMapping("/savePost")
    public @ResponseBody
    Integer save(@RequestBody Post post){
        String content = post.getContent().replace("|", "\"");
        post.setContent(content);
        Timestamp time = Timestamp.valueOf(Util.nowTime());
        post.setTime(time);
        postService.save(post);
        subjectService.executeHql("update Subject set lasttime = ? where id=?",
                new Object[]{time, post.getSid()});
        return 1;
    }
}
