package com.platform.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/11.
 */
@Entity
@Table(name = "user")
public class User implements Serializable{
    private Integer id;
    private String email;
    private String name;
    private String password;
    private String type; //用户的类型
    private String avatar;//用户的头像
    private Integer state;// 用户状态 1：已激活 0：未激活
    private String videoinfo; //用户以后一次课程信息

    public User() {
    }

    public User(String password) {
        this.password = password;
    }

    public User(Integer id, String name, String avatar) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
    }

    public User(String email, String name, String password, String type, String avatar, Integer state, String videoinfo) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.type = type;
        this.avatar = avatar;
        this.state = state;
        this.videoinfo = videoinfo;
    }

    public User(String email, String name, String password, String type, String avatar, Integer state) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.type = type;
        this.avatar = avatar;
        this.state = state;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getVideoinfo() {
        return videoinfo;
    }

    public void setVideoinfo(String videoinfo) {
        this.videoinfo = videoinfo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", avatar='" + avatar + '\'' +
                ", state=" + state +
                '}';
    }
}
