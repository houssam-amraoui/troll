package com.houssam.trollat.model;

import java.util.Date;

public class Replay {
    private String imagelink;
    private String name;
    private String body;
    private Date date;
    private int nblike;

    public Replay(){

    }

    public Replay(String imagelink, String name, String body, Date date, int nblike) {
        this.imagelink = imagelink;
        this.name = name;
        this.body = body;
        this.date = date;
        this.nblike = nblike;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNblike() {
        return nblike;
    }

    public void setNblike(int nblike) {
        this.nblike = nblike;
    }
}
