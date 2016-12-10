package com.platform.entities;

import javax.persistence.*;

/**
 * Created by Administrator on 2015/11/2.
 */
@Entity
@Table(name = "levecourse")
public class LeveCourse {

    private Integer id;
    private String coursename;
    private String description;
    private Integer typeid;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public LeveCourse(String coursename, String description) {
        this.coursename = coursename;
        this.description = description;
    }

    public LeveCourse(Integer id, String coursename) {
        this.id = id;
        this.coursename = coursename;
    }

    public LeveCourse(String coursename, String description, Integer typeid) {
        this.coursename = coursename;
        this.description = description;
        this.typeid = typeid;
    }

    public LeveCourse(){}

    @Override
    public String toString() {
        return "LeveCourse{" +
                "id=" + id +
                ", coursename='" + coursename + '\'' +
                ", description='" + description + '\'' +
                ", typeid=" + typeid +
                '}';
    }
}
