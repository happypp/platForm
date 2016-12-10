package com.platform.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2015/11/11.
 */
@Entity
@Table(name = "post")
public class Post {

    private Integer id;
    private Integer sid;
    private Integer uid;
    private String content;
    private Timestamp time;
    private Integer pid;

    public Post() {
    }

    public Post(Integer id, Integer sid, Integer uid,
                java.util.Date time) {
        this.id = id;
        this.sid = sid;
        this.uid = uid;
        this.time = (java.sql.Timestamp) time;
    }

    public Post(Integer sid, Integer uid, String content, Timestamp time) {
        this.sid = sid;
        this.uid = uid;
        this.content = content;
        this.time = time;
    }

    public Post(Integer sid, Integer uid, String content, Timestamp time, Integer pid) {
        this.sid = sid;
        this.uid = uid;
        this.content = content;
        this.time = time;
        this.pid = pid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", sid=" + sid +
                ", uid=" + uid +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", pid=" + pid +
                '}';
    }
}
