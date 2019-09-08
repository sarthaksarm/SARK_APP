package com.sark.android.TeamDevAchi;

public class Blog {
    private String desc;
    private String title;
    private String image;
    private String detail;

    public Blog(String desc, String title, String image, String detail) {
        this.desc = desc;
        this.title = title;
        this.image = image;
        this.detail=detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public Blog()
    {

    }
}
