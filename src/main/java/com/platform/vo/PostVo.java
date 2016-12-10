package com.platform.vo;

import com.platform.entities.Post;
import com.platform.entities.User;
import com.platform.util.Util;

import java.util.List;

/**
 * Created by Administrator on 2015/11/18.
 */
public class PostVo {

    private Integer uid;
    private Integer pid;
    private String userName;
    private String userAvatar;
    private String content;
    private String postTime;
    private Integer rpid;
    private List<Post> posts;
    private List<RPostVo> rPostVos;
    public PostVo() {
    }

    public PostVo(User u,String userAvatar,List<RPostVo> rPostVos) {
        this.uid = u.getId();
        this.userName = u.getName();
        this.userAvatar = userAvatar;
        this.rPostVos = rPostVos;
    }

    public PostVo(User u, Post p,String userAvatar) {
        this.uid = u.getId();
        this.pid = p.getId();
        this.userName = u.getName();
        this.userAvatar = userAvatar;
        this.content = p.getContent();
        this.postTime = Util.getSimpleTimeStr(p.getTime());
    }

//    public PostVo(User u, Post p,String userAvatar,List<Post> posts) {
//        this.uid = u.getId();
//        this.pid = p.getId();
//        this.rpid = p.getPid();
//        this.userName = u.getName();
//        this.userAvatar = userAvatar;
//        this.content = p.getContent();
//        this.postTime = Util.getSimpleTimeStr(p.getTime());
//        this.posts = posts;
//    }

    public PostVo(User u, Post p,String userAvatar,List<RPostVo> rPostVos) {
        this.uid = u.getId();
        this.pid = p.getId();
        this.rpid = p.getPid();
        this.userName = u.getName();
        this.userAvatar = userAvatar;
        this.content = p.getContent();
        this.postTime = Util.getSimpleTimeStr(p.getTime());
        this.rPostVos = rPostVos;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public Integer getRpid() {
        return rpid;
    }

    public void setRpid(Integer rpid) {
        this.rpid = rpid;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<RPostVo> getrPostVos() {
        return rPostVos;
    }

    public void setrPostVos(List<RPostVo> rPostVos) {
        this.rPostVos = rPostVos;
    }

    @Override
    public String toString() {
        return "PostVo{" +
                "uid=" + uid +
                ", pid=" + pid +
                ", userName='" + userName + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", content='" + content + '\'' +
                ", postTime='" + postTime + '\'' +
                ", rpid=" + rpid +
                ", posts=" + posts +
                ", rPostVos=" + rPostVos +
                '}';
    }
}
