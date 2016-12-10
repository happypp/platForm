package com.platform.vo;

import com.platform.entities.LeveCourse;

import java.util.List;

/**
 * Created by Administrator on 2015/11/25.
 */
public class CourseVo {
    private Integer id;
    private List<LeveCourse> leveCourses;
    private String type;

    public List<LeveCourse> getLeveCourses() {
        return leveCourses;
    }

    public void setLeveCourses(List<LeveCourse> leveCourses) {
        this.leveCourses = leveCourses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CourseVo(List<LeveCourse> leveCourses, String type) {
        this.leveCourses = leveCourses;
        this.type = type;
    }

    public CourseVo(Integer id, List<LeveCourse> leveCourses, String type) {
        this.id = id;
        this.leveCourses = leveCourses;
        this.type = type;
    }

    @Override
    public String toString() {
        return "CourseVo{" +
                "leveCourses=" + leveCourses +
                ", type='" + type + '\'' +
                '}';
    }
}
