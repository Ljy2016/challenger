package com.azadljy.challenger.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class PictureModel {
    @Id(autoincrement = true)
    private Long id;

    private String pictureUrl;

    private String picDescribe;

    private String pId;
    private Date date;
    @Generated(hash = 595409370)
    public PictureModel(Long id, String pictureUrl, String picDescribe, String pId,
            Date date) {
        this.id = id;
        this.pictureUrl = pictureUrl;
        this.picDescribe = picDescribe;
        this.pId = pId;
        this.date = date;
    }
    @Generated(hash = 789160052)
    public PictureModel() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getPictureUrl() {
        return this.pictureUrl;
    }
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
    public String getPicDescribe() {
        return this.picDescribe;
    }
    public void setPicDescribe(String picDescribe) {
        this.picDescribe = picDescribe;
    }
    public String getPId() {
        return this.pId;
    }
    public void setPId(String pId) {
        this.pId = pId;
    }
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setId(Long id) {
        this.id = id;
    }


}
