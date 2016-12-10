package com.platform.entities;

import javax.persistence.*;

/**
 * Created by Administrator on 2015/12/2.
 */
@Entity
@Table(name = "chapter")
public class Chapter {

    private Integer id;
    private String chaptername;
    private Integer courseid;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChaptername() {
        return chaptername;
    }

    public void setChaptername(String chaptername) {
        this.chaptername = chaptername;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }


    public Chapter() {
    }

    public Chapter(Integer id) {
        this.id = id;
    }

    public Chapter(Integer id, String chaptername) {
        this.id = id;
        this.chaptername = chaptername;
    }

    public Chapter(String chaptername, Integer courseid) {
        this.chaptername = chaptername;
        this.courseid = courseid;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", chaptername='" + chaptername + '\'' +
                ", courseid=" + courseid +
                '}';
    }
}
