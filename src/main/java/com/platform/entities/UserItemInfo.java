package com.platform.entities;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by lenovo on 2015/12/13.
 */
public class UserItemInfo {
    private Integer uid;
    private String name;
    private MultipartFile avatar;

    public UserItemInfo() {
    }

    public UserItemInfo(Integer uid, String name, MultipartFile avatar) {
        this.uid = uid;
        this.name = name;
        this.avatar = avatar;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "UserItemInfo{" +
                "avatar=" + avatar +
                ", name='" + name + '\'' +
                ", uid=" + uid +
                '}';
    }
}
