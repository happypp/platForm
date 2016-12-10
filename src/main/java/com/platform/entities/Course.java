package com.platform.entities;

import javax.persistence.*;

/**
 * Created by Administrator on 2015/11/2.
 */

@Entity
@Table(name = "course")
public class Course {

    private Integer id;
    private String courseimg;
    private String title;
    private String tip;
    private Integer playnum;
    private String needinfo;
    private String studyinfo;
    private Integer lid;
    private String courseware;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseimg() {
        return courseimg;
    }

    public void setCourseimg(String courseimg) {
        this.courseimg = courseimg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Integer getPlaynum() {
        return playnum;
    }

    public void setPlaynum(Integer playnum) {
        this.playnum = playnum;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getNeedinfo() {
        return needinfo;
    }

    public void setNeedinfo(String needinfo) {
        this.needinfo = needinfo;
    }

    public String getStudyinfo() {
        return studyinfo;
    }

    public void setStudyinfo(String studyinfo) {
        this.studyinfo = studyinfo;
    }

    public String getCourseware() {
        return courseware;
    }

    public void setCourseware(String courseware) {
        this.courseware = courseware;
    }

    public Course() {
    }

    public Course(Integer id, String title, Integer playnum) {
        this.id = id;
        this.title = title;
        this.playnum = playnum;
    }

    public Course(Integer id) {
        this.id = id;
    }

    public Course(String title) {
        this.title = title;
    }

    public Course(String needinfo, String studyinfo) {
        this.needinfo = needinfo;
        this.studyinfo = studyinfo;
    }

    public Course(String title, String needinfo, String studyinfo) {
        this.title = title;
        this.needinfo = needinfo;
        this.studyinfo = studyinfo;
    }

    public Course(String courseimg, String title, String tip, Integer playnum, String needinfo, String studyinfo, Integer lid) {
        this.courseimg = courseimg;
        this.title = title;
        this.tip = tip;
        this.playnum = playnum;
        this.needinfo = needinfo;
        this.studyinfo = studyinfo;
        this.lid = lid;
    }

    public Course(String courseimg, String title, String tip, Integer playnum, Integer lid) {
        this.courseimg = courseimg;
        this.title = title;
        this.tip = tip;
        this.playnum = playnum;
        this.lid = lid;
    }

    public Course(String courseimg, String title, String tip, Integer playnum, String needinfo, String studyinfo, Integer lid, String courseware) {
        this.courseimg = courseimg;
        this.title = title;
        this.tip = tip;
        this.playnum = playnum;
        this.needinfo = needinfo;
        this.studyinfo = studyinfo;
        this.lid = lid;
        this.courseware = courseware;
    }

    public Course(String title, String needinfo, String studyinfo, String courseware) {
        this.title = title;
        this.needinfo = needinfo;
        this.studyinfo = studyinfo;
        this.courseware = courseware;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseimg='" + courseimg + '\'' +
                ", title='" + title + '\'' +
                ", tip='" + tip + '\'' +
                ", playnum=" + playnum +
                ", needinfo='" + needinfo + '\'' +
                ", studyinfo='" + studyinfo + '\'' +
                ", lid=" + lid +
                '}';
    }
}
