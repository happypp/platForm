package com.platform.vo;

import com.platform.entities.Post;
import com.platform.entities.User;

/**
 * Created by lenovo on 2016/1/24.
 */
public class RPostVo {
    private Post post;
    private User user;
    private String time;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public RPostVo() {
    }

    public RPostVo(Post post, User user, String time) {
        this.post = post;
        this.user = user;
        this.time = time;
    }


    @Override
    public String toString() {
        return "RPostVo{" +
                "post=" + post +
                ", user=" + user +
                ", time='" + time + '\'' +
                '}';
    }
}
