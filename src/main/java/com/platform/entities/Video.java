package com.platform.entities;

import javax.persistence.*;

/**
 * Created by Administrator on 2015/12/2.
 */
@Entity
@Table(name = "video")
public class Video {

    private Integer id;
    private String videoname;
    private String videoaddr;
    private Integer chapterid; //章节ID

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    public String getVideoaddr() {
        return videoaddr;
    }

    public void setVideoaddr(String videoaddr) {
        this.videoaddr = videoaddr;
    }

    public Integer getChapterid() {
        return chapterid;
    }

    public void setChapterid(Integer chapterid) {
        this.chapterid = chapterid;
    }

    public Video() {
    }

    public Video(String videoaddr, Integer chapterid) {
        this.videoaddr = videoaddr;
        this.chapterid = chapterid;
    }

    public Video(Integer id, String videoaddr) {
        this.id = id;
        this.videoaddr = videoaddr;
    }

    public Video(String videoname) {
        this.videoname = videoname;
    }

    public Video(String videoname, String videoaddr, Integer chapterid) {
        this.videoname = videoname;
        this.videoaddr = videoaddr;
        this.chapterid = chapterid;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", videoname='" + videoname + '\'' +
                ", videoaddr='" + videoaddr + '\'' +
                ", chapterid=" + chapterid +
                '}';
    }
}
