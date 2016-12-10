package com.platform.entities;

import javax.persistence.*;

/**
 * Created by Administrator on 2015/11/25.
 */
@Entity
@Table(name = "coursetype")
public class CourseType {

    private Integer id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CourseType(String name) {
        this.name = name;
    }

    public CourseType() {
    }
}
