package com.platform.vo;

import com.platform.entities.Chapter;
import com.platform.entities.Course;
import com.platform.entities.Video;

import java.util.List;

/**
 * Created by Administrator on 2015/12/2.
 */
public class ChapterVideoVo {

    private Chapter chapter;
    private List<Video> videos;
    private Video video;
    private Course course;

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ChapterVideoVo() {
    }

    public ChapterVideoVo(Chapter chapter, Video video, Course course) {
        this.chapter = chapter;
        this.video = video;
        this.course = course;
    }

    public ChapterVideoVo(Chapter chapter, List<Video> videos) {
        this.chapter = chapter;
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "ChapterVideoVo{" +
                "chapter=" + chapter +
                ", videos=" + videos +
                '}';
    }
}
