package com.platform.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2015/11/11.
 */
@Entity
@Table(name = "subject")
public class Subject {

    private Integer id;
    private String title;
    private String content;
    private Integer uid;
    private Timestamp time;  //主题发布时间
    private Integer visits;
    private Timestamp lasttime; //最后回复时间

    public Subject() {
    }

    public Subject(String title, String content, Integer uid, Timestamp time, Integer visits, Timestamp lasttime) {
        this.title = title;
        this.content = content;
        this.uid = uid;
        this.time = time;
        this.visits = visits;
        this.lasttime = lasttime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public Timestamp getLasttime() {
        return lasttime;
    }

    public void setLasttime(Timestamp lasttime) {
        this.lasttime = lasttime;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", uid=" + uid +
                ", time=" + time +
                ", visits=" + visits +
                ", lasttime=" + lasttime +
                '}';
    }
}
