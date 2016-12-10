package com.platform.vo;

/**
 * Created by lenovo on 2016/1/15.
 */
public class LcourseTypeVo {

    private Integer id;
    private Integer ct_id;
    private Integer l_id;
    private String ct_name;
    private String l_name;

    public LcourseTypeVo(Integer ct_id, Integer l_id, String ct_name, String l_name) {
        this.ct_id = ct_id;
        this.l_id = l_id;
        this.ct_name = ct_name;
        this.l_name = l_name;
    }

    public LcourseTypeVo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCt_id() {
        return ct_id;
    }

    public void setCt_id(Integer ct_id) {
        this.ct_id = ct_id;
    }

    public Integer getL_id() {
        return l_id;
    }

    public void setL_id(Integer l_id) {
        this.l_id = l_id;
    }

    public String getCt_name() {
        return ct_name;
    }

    public void setCt_name(String ct_name) {
        this.ct_name = ct_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    @Override
    public String toString() {
        return "LcourseTypeVo{" +
                "id=" + id +
                ", ct_id=" + ct_id +
                ", l_id=" + l_id +
                ", ct_name='" + ct_name + '\'' +
                ", l_name='" + l_name + '\'' +
                '}';
    }
}
