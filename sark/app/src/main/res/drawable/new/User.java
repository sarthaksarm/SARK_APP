package com.example.sarthakmishra.sark;

public class User {

    private String title;
    private String descrip;
    private String key;
    private String key2;
    private String eventtime;
    private String dateofnotific;

    public User(){

    }
    public User(String title, String descrip, String key, String eventtime, String dateofnotific, String key2) {
        this.title = title;
        this.descrip = descrip;
        this.key=key;
        this.eventtime=eventtime;
        this.dateofnotific=dateofnotific;
        this.key2=key2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getDateofnotific() {
        return dateofnotific;
    }

    public void setDateofnotific(String dateofnotific) {
        this.dateofnotific = dateofnotific;
    }

    public String getEventtime() {
        return eventtime;
    }

    public void setEventtime(String eventtime) {
        this.eventtime = eventtime;
    }
}